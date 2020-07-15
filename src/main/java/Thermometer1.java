import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;

import java.util.Random;

public class Thermometer1 extends AbstractThermometer {

  public Thermometer1(IMqttClient client) throws MqttException {
    super(client);
  }

  @Override
  protected String getTopic() {
    return "/sensor/data/therm1";
  }

  @Override
  public void connect() throws MqttException {
    client.connect();
    JSONObject json = new JSONObject();
    json.put("serialNumber", getID());
    json.put("sensorType", "Thermometer 1");
    client.publish("sensor/connect", new MqttMessage(json.toString().getBytes()));
  }

  // generate an mqtt message with sensor data
  @Override
  protected MqttMessage getMessage() {
    JSONObject json = new JSONObject();
    json.put("serialNumber", getID());
    json.put("sensorType", "Thermometer");
    json.put("temp", getTemperature());
    json.put("sensorModel", "T1000");
    json.put("hum", getHumidity());
    return new MqttMessage(json.toString().getBytes());
  }

  @Override
  public String toString() {
    return "Thermometer type 1: " + getID();
  }
}
