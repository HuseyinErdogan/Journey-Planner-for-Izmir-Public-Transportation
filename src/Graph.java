import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph {
	private HashMap<Integer,Vertex> vertices;
	private HashMap<String,Edge> edges;
	private HashMap<Integer,Line> lines;
	
	Graph() {
		this.vertices = new HashMap<>();
		this.edges = new HashMap<>();
		this.lines=new HashMap<>();
	}

    public List<Integer> getNeig(Vertex v) {
    	List<Integer> arrList = new ArrayList<Integer>();
    	for (Vertex i:vertices.values()) {
			for (Edge e:edges.values()) {
				if (e.getSource()==v) {
					if (e.getDestination()==i) {
						arrList.add(i.getStopId());
					}
				}
			}
		}
    	return arrList;
    }
    public Vertex getPathOnLine(Vertex v,int lineId, int direction) {//AYNI LINE ÜZERİNDEKİ BİR SONRAKİ DURAĞI BULUR
    	try {
    		
        	
		} catch (NullPointerException e) {
			System.err.println("GetPathOnLine: Girilen vertex null");
		}
    	for (Edge e:edges.values()) {

			if (e.getSource().getStopId()==v.getStopId()&&e.getLineId()==lineId &&e.getDirection()==direction) {
				return e.getDestination();
			}
		}
    	return null;
    	
    }
    public Vertex getBeforePathOnLine(Vertex v,int lineId) {
    	
    	for (Edge e:edges.values()) {
			if (e.getDestination()==v&&e.getLineId()==lineId) {
				return e.getSource();
			}
		}
    	return null;
    	
    }
    public int findDistance(Vertex source, Vertex destination, int line) {//İKİ DURAK ARASINDAKİ MESAFEYİ BULUR
    	for (Edge e: edges.values()) {
			if (source==e.getSource()&&destination==e.getDestination()&&line==e.getLineId()) {
				return e.getDistance();
			}
		}
    	return 0;
    	
    	
    }
    
    
    
    
    public int neigCount(List<Integer> a) {
    	
    	return a.size();
    }
    public int lineCheck(Vertex start,Vertex end) {//AYNI LİNEDA MI BULUNUYORLAR
    	
    	for (Edge ed:edges.values()) {
			if (ed.getSource()==start) {
				for (Edge ed2:edges.values()) {
					if (ed2.getDestination()==end) {
						if (ed.getLineId()==ed2.getLineId() && ed.getOrder()<ed2.getOrder() && ed.getDirection()==ed2.getDirection()) {
							return ed.getLineId();
						}
					}
				}
			}
		}
    	return 0;
    }
    public int stopCount(Vertex start,Vertex end, int lineId, int direction) { //İKİ DURAK ARASINDAKİ TOPLAM DURAK SAYISINI HESAPLAR
    	
    	for (Edge ed:edges.values()) {
			if (ed.getSource()==start && ed.getLineId()==lineId && ed.getDirection()==direction) {
				for (Edge ed2:edges.values()) {
					if (ed2.getDestination()==end && ed2.getLineId()==lineId && ed2.getDirection()==direction) {
						return ed2.getOrder()-ed.getOrder()+1;
					}
				}
			}
		}
    	return -1;
    }

    public Vertex findDestination(Vertex origin,int lineId, int direction, int type) { // BAŞI VE LINEID'Sİ GİRİLEN EDGE'İN DESTINATIONUNU BULUR
    	try {
    		for (Edge e:edges.values()) {
    			if (e.getSource().getStopId()==origin.getStopId()&&e.getLineId()==lineId&&e.getDirection()==direction) {
    				if (type==2) {
    					
    					if (e.getDestination().getValue()>=e.getSource().getValue()+e.getDistance()) {
    						e.getDestination().setValue(e.getSource().getValue()+e.getDistance());
    						return e.getDestination();
    					}
    					else 
    						return null;
    					
    				}
    				else if (type==1) {
    					if (e.getDestination().getValue()>=e.getSource().getValue()+1) {
    						e.getDestination().setValue(e.getSource().getValue()+1);
    						return e.getDestination();
    					}
    					else 
    						return null;
    				}				
    		
    			}
    		}
			
		} catch (NullPointerException e) {
			System.err.println("FindDestination: Girilen vertex değeri null!");
		}
    	
    	
    	
    	return null;
    }
    public Edge findEdge(Vertex origin, Vertex destination, int lineId) {
    	for (Edge e:edges.values()) {
			if (e.getSource()==origin&&e.getDestination()==destination&&lineId==e.getLineId()) {
				return e;
			}
		}
    	return null;
    }
    public int findDirection(Vertex start, Vertex end, int lineID) {
    	try {
    		for (Edge e:edges.values()) {
    			if (e.getSource()==start && e.getLineId()==lineID) {
    				for (Edge e2:edges.values()) {
    					if (e2.getDestination()==end) {
    						if (e.getDirection()==e2.getDirection()) {
    							return e.getDirection();
    						}
    					}
    				}
    			}
    		}
		} catch (NullPointerException e) {
			System.err.println("FindDirection: Girilen vertexlerden biri null!");
		}
    	
    	return -1;
    }
    public int calculateDistance(Vertex start, Vertex end , int lineId , int direction, int type) {//LINE'I BİLİNEN İKİ DURAK ARASINDAKİ MESAFEYİ HESAPLAR
    	if (type==1) {
			return 1;
		}
    	int distance=0;
    	Vertex tempStart = start;

    	while(true) {
    		
    		distance+=findDistance(tempStart,getPathOnLine(tempStart,lineId,direction),lineId);
    		tempStart=getPathOnLine(tempStart,lineId,direction);
    		if (tempStart==end) {
				break;
			}
    	}
    	return distance;
    	
    }

	public void addVertex(Vertex vertex) {
		vertices.put(vertex.getStopId(), vertex);
	}
	public Vertex getVertex(int v) {
		
		return vertices.get(v);
	}
	public void addEdge(Edge edge) {
		
		edges.put(edge.getSource().getStopId()+"-"+edge.getDestination().getStopId()+"-"+edge.getLineId(), edge);
		
	}
	public Edge getEdge(int originStop, int destinationStop, int lineId) {
		
		return edges.get(originStop+"-"+destinationStop+"-"+lineId);
		
	}
	public void printEdges() {
		for (Edge e:edges.values()) {
			System.out.println(e.getSource().getStopId()+"----"+e.getDestination().getStopId());
		}
	}
	public void addLineList(Line l) {
		lines.put(l.getLineId(),l);
	}
	public Line getLine(int id) {
		return lines.get(id);
	}
	public int checkNeighbor(Vertex v1, Vertex v2) {
		for (int i = 0; i < v2.getNeighborVertex().size(); i++) {
			if (Integer.parseInt(v2.getNeighborVertex().get(i)[0])==v1.getStopId()) {
				return Integer.parseInt(v2.getNeighborVertex().get(i)[1]);
			}
		}
		return -1;
	}
	
	public HashMap<Integer, Vertex> getVertices() {
		return vertices;
	}
	public void setVertices(HashMap<Integer, Vertex> vertices) {
		this.vertices = vertices;
	}
	public HashMap<String, Edge> getEdges() {
		return edges;
	}
	public void setEdges(HashMap<String, Edge> edges) {
		this.edges = edges;
	}
	public HashMap<Integer, Line> getLines() {
		return lines;
	}
	public void setLines(HashMap<Integer, Line> lines) {
		this.lines = lines;
	}
	public void print(){


		for (Vertex e : vertices.values()) {
			System.out.println(e.getName());
		}
	}

	public  Iterable<Vertex> vertices()
	{
		return vertices.values();
	}

	public  Iterable<Edge> edges()
	{
		return edges.values();
	}

	public int size()
	{
		return vertices.size();
	}
}
