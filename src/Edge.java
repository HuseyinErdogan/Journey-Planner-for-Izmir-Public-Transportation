import java.util.ArrayList;

public class Edge{
	private Vertex source;
	private Vertex destination;
	private int lineId;
	private int distance;
	
	private int order;
	private int direction;
	public Edge(int lineId, Vertex source, Vertex destination,int direction, int order) {
		this.lineId=lineId;
		this.source = source;
		this.destination = destination;
		this.order=order;
		this.direction=direction;
		distance=625;
	}

	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public int getLineId() {
		return lineId;
	}
	
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		
		this.distance = distance;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public void setLineId(int lineId) {
		this.lineId = lineId;
	}

	public Vertex getSource() {
		return source;
	}

	public void setSource(Vertex source) {
		this.source = source;
	}

	public Vertex getDestination() {
		return destination;
	}

	public void setDestination(Vertex destination) {
		this.destination = destination;
	}


}
 
