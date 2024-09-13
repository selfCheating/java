package Restful;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

	public class Rest_Get_API {
	    public static void main(String[] args) {
	        String json = "[\n" +
	                "    {\"id\": \"1\", \"name\": \"Google Pixel 6 Pro\", \"data\": {\"color\": \"Cloudy White\", \"capacity\": \"128 GB\"}},\n" +
	                "    {\"id\": \"2\", \"name\": \"Apple iPhone 12 Mini, 256GB, Blue\", \"data\": null},\n" +
	                "    {\"id\": \"3\", \"name\": \"Apple iPhone 12 Pro Max\", \"data\": {\"color\": \"Cloudy White\", \"capacity GB\": 512}},\n" +
	                "    {\"id\": \"4\", \"name\": \"Apple iPhone 11, 64GB\", \"data\": {\"price\": 389.99, \"color\": \"Purple\"}},\n" +
	                "    {\"id\": \"5\", \"name\": \"Samsung Galaxy Z Fold2\", \"data\": {\"price\": 689.99, \"color\": \"Brown\"}},\n" +
	                "    {\"id\": \"6\", \"name\": \"Apple AirPods\", \"data\": {\"generation\": \"3rd\", \"price\": 120}},\n" +
	                "    {\"id\": \"7\", \"name\": \"Apple MacBook Pro 16\", \"data\": {\"year\": 2019, \"price\": 1849.99, \"CPU model\": \"Intel Core i9\", \"Hard disk size\": \"1 TB\"}},\n" +
	                "    {\"id\": \"8\", \"name\": \"Apple Watch Series 8\", \"data\": {\"Strap Colour\": \"Elderberry\", \"Case Size\": \"41mm\"}},\n" +
	                "    {\"id\": \"9\", \"name\": \"Beats Studio3 Wireless\", \"data\": {\"Color\": \"Red\", \"Description\": \"High-performance wireless noise cancelling headphones\"}},\n" +
	                "    {\"id\": \"10\", \"name\": \"Apple iPad Mini 5th Gen\", \"data\": {\"Capacity\": \"64 GB\", \"Screen size\": 7.9}},\n" +
	                "    {\"id\": \"11\", \"name\": \"Apple iPad Mini 5th Gen\", \"data\": {\"Capacity\": \"254 GB\", \"Screen size\": 7.9}},\n" +
	                "    {\"id\": \"12\", \"name\": \"Apple iPad Air\", \"data\": {\"Generation\": \"4th\", \"Price\": \"419.99\", \"Capacity\": \"64 GB\"}},\n" +
	                "    {\"id\": \"13\", \"name\": \"Apple iPad Air\", \"data\": {\"Generation\": \"4th\", \"Price\": \"519.99\", \"Capacity\": \"256 GB\"}}\n" +
	                "]";

	        ObjectMapper mapper = new ObjectMapper();

	        try {
	            JsonNode rootNode = mapper.readTree(json);

	            int totalProducts = 0;
	            double totalPrice = 0.0;

	            for (JsonNode node : rootNode) {
	                String id = node.get("id").asText();
	                String name = node.get("name").asText();
	                JsonNode dataNode = node.get("data");

	                System.out.print("ID: " + id + ", Name: " + name);

	                if (dataNode != null && dataNode.isObject()) {
	                    Iterator<Map.Entry<String, JsonNode>> fields = dataNode.fields();
	                    while (fields.hasNext()) {
	                        Map.Entry<String, JsonNode> field = fields.next();
	                        String key = field.getKey();
	                        String value = field.getValue().toString();
	                        System.out.print(", " + key + ": " + value);
	                        
	                        // If the field is "price" and is a number, add it to the total price
	                        if ("price".equalsIgnoreCase(key)) {
	                            if (field.getValue().isNumber()) {
	                                totalPrice += field.getValue().asDouble();
	                            }
	                        }
	                    }
	                } else {
	                    System.out.print(", Data: null");
	                }

	                System.out.println();
	                totalProducts++;
	            }

	            System.out.println("Total number of products: " + totalProducts);
	            System.out.println("Total price of products: " + totalPrice);
	            System.out.println("Average price of products: " + (totalProducts > 0 ? totalPrice / totalProducts : 0));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
