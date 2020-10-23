import java.util.HashMap;

public class Line {
	private int lineId;
	private int lineNo;
	private String name;
	private int vehicleTypeId;
	private HashMap<String,Vertex> stops;
	
	public Line(int lineId, int lineNo, String name, int vehicleTypeId) {
		this.lineId=lineId;
		this.lineNo=lineNo;
		this.name=name;
		this.vehicleTypeId=vehicleTypeId;	
		stops=new HashMap<>();
	}

	public int getLineId() {
		return lineId;
	}
	public void setLineId(int lineId) {
		this.lineId = lineId;
	}
	public int getLineNo() {
		return lineNo;
	}
	public void setLineNo(int lineNo) {
		this.lineNo = lineNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getVehicleTypeId() {
		return vehicleTypeId;
	}
	public void setVehicleTypeId(int vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}
	
	
}
