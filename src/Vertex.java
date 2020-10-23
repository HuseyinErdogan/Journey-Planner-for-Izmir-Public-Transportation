import java.util.ArrayList;

public class Vertex {
	private String name;
	private int stopId;
	private String coordinateX,coordinateY;
	private int vehicleTypeId;
	private ArrayList<String[]> Lines;
	private ArrayList<String[]> neighborVertex;
	private int value;
	private String[] temp= new String[2];
	
	 public Vertex(int stopId, String name, String coordinateX, String coordinateY, int vehicleTypeId) {

		 	this.stopId=stopId;
	        this.name = name;
	        this.coordinateX=coordinateX;
	        this.coordinateY=coordinateY;
	        this.vehicleTypeId=vehicleTypeId;
	        Lines = new ArrayList<>();
	        neighborVertex = new ArrayList<>();
	        value=Integer.MAX_VALUE;
	        temp[0]=Integer.toString(stopId);
	        temp[1]="-1";
	        neighborVertex.add(temp);
	        

	    }
	 
	 public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void addNeighbor(String s) {

		String[] temp = new String[2];
		temp = s.split(":");
		neighborVertex.add(temp);
		 
	 }
	 
	 public ArrayList<String[]> getNeighborVertex() {
		return neighborVertex;
	}

	public void setNeighborVertex(ArrayList<String[]> neighborVertex) {
		this.neighborVertex = neighborVertex;
	}


	public void setLines(ArrayList<String[]> Lines) {
		this.Lines = Lines;
	}

	public int getStopId() {
		return stopId;
	}

	public void setStopId(int stopId) {
		this.stopId = stopId;
	}

	public String getCoordinateX() {
		return coordinateX;
	}

	public void setCoordinateX(String coordinateX) {
		this.coordinateX = coordinateX;
	}

	public String getCoordinateY() {
		return coordinateY;
	}

	public void setCoordinateY(String coordinateY) {
		this.coordinateY = coordinateY;
	}

	public int getVehicleTypeId() {
		return vehicleTypeId;
	}

	public void setVehicleTypeId(int vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}

	public void addLine(String e)
	 {	
		String[] temp=e.split("=");
		 Lines.add(temp);  
	 }
	 
	 public ArrayList<String[]> getLines()
	 {
		 return this.Lines;
	 }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
	