package charen.gtv.projectx;


public class DeviceSettings {

	private static DeviceSettings instance;
	
	public static DeviceSettings Instance() {
		return instance != null ? instance : (instance = new DeviceSettings());
	}
	
	public int width;
	public int height;
	
}
