import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.util.ajax.JSON;
import org.eclipse.jetty.util.ajax.JSONPojoConvertor;
import pojo.ErrorPojo;
import pojo.IPInfoPojo;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;


public class Main {
    private static final String defaultHost = "localhost";
    private static final String defaultPort = "8080";
    private static final String geoipServlet = "geoip";
    private static final String geoIPParam = "ip";



    public static void main(String[] args) throws Exception {
        HttpClient httpClient = new HttpClient();

        ContentResponse response = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        httpClient.setConnectTimeout(2000);
        httpClient.start();

        String temp, host, port;

        System.out.print("IP сервера [" + defaultHost + "]: ");
        temp = reader.readLine().replaceAll("\\s+", "");
        host = (temp.isEmpty() ? defaultHost : temp);

        while (true) {
            System.out.print("Порт сервера [" + defaultPort  + "]: ");
            temp = reader.readLine().replaceAll("\\s+", "");
            port = (temp.isEmpty() ? defaultPort : temp);
            if (port.matches("\\d+")) {
                break;
            } else {
                System.out.println("Неверный номер порта: \'" + port + "\'");
            }
        }

        System.out.println("Для выхода введите: \'exit\'");
        while (true) {
            System.out.print("IP адрес: ");
            String inputData = reader.readLine().replaceAll("\\s+","");


            if (inputData.equals("exit")) {
                httpClient.stop();
                System.exit(0);
            }


            // отправка и получение запроса
            try {
                response = httpClient.GET(String.format("http://%s:%s/%s?%s=%s", host, port, geoipServlet, geoIPParam, inputData));
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getCause().getMessage());
            }

            if (response != null) {
                JSON json = new JSON();
                Map<String, Object> ipInfo = (Map<String, Object>) json.fromJSON(response.getContentAsString());

                JSONPojoConvertor jsonPojoConvertor;

                boolean statusJsonRequest = (Boolean) ipInfo.get("success");

                if (statusJsonRequest) {
                    jsonPojoConvertor = new JSONPojoConvertor(IPInfoPojo.class);
                    IPInfoPojo ipInfoPojo;
                    ipInfoPojo = (IPInfoPojo) jsonPojoConvertor.fromJSON(ipInfo);
                    System.out.println(ipInfoPojo.toString());

                } else if (!statusJsonRequest) {
                    jsonPojoConvertor = new JSONPojoConvertor(ErrorPojo.class);
                    ErrorPojo errorPojo;
                    errorPojo = (ErrorPojo) jsonPojoConvertor.fromJSON(ipInfo);
                    System.out.println(errorPojo.toString());
                }
                response = null;
            }

        }

    }

}
