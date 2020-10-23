import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
	Graph graph;
	public ReadFile(Graph graph) {
		this.graph=graph;
	}
	public void readStop(){	
		File stopTXT = new File("/Stop.txt");		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(stopTXT));
			

		    String st; 
		    String[] stArray = new String[6];
		    String[] stTemp;
		    st=br.readLine();
		    while ((st = br.readLine()) != null) {
		    	stArray=st.split(";");
		    	Vertex temp = new Vertex(Integer.parseInt(stArray[0]),stArray[1],stArray[2],stArray[3],Integer.parseInt(stArray[4]));	    	
		    	graph.addVertex(temp);
		    	if (!stArray[5].equals("NULL")) {
		    		stTemp=stArray[5].split("\\.");
			    	for (int i = 0; i < stTemp.length; i++) {
						temp.addNeighbor(stTemp[i]);
					}
				}
		    	
		    	
		    }

			  
		} catch (FileNotFoundException e) {
			System.out.println("Error detected while reading stop txt!!");
		} catch (IOException e) {
			System.out.println("Error detected while reading stop txt!!");
		}
		
		
	
	}

	public void readDistance() {
		File distanceTXT = new File("/Distance.txt");		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(distanceTXT));
			

		    String st; 
		    String[] stArray = new String[3];
		    st=br.readLine();
		   
		    while ((st = br.readLine()) != null) {
		    	stArray=st.split(";");
		    	 boolean flag=false;
		    	 boolean flag2=false;
		    	 Vertex tempV=null;
		    	 Vertex tempV2=null;
		    	for (Vertex v:graph.vertices()) {
					if (v.getStopId()==Integer.parseInt(stArray[0])) {
						flag=true;
						tempV=v;
						break;
					}
					
				
				}
		    	for (Vertex v2:graph.vertices()) {
		    		if (v2.getStopId()==Integer.parseInt(stArray[1])) {
						flag2=true;
						tempV2=v2;
						break;
					}
				}
		    	if (flag && flag2) { //DURAK ATLAYIP DISTANCE DEĞERİ VERİLMİŞSE
		    		for (Edge e:graph.edges()) {
						if ((e.getSource()==tempV&&e.getDestination()==tempV2)  ) {
							graph.getEdge(Integer.parseInt(stArray[0]), Integer.parseInt(stArray[1]),e.getLineId()).setDistance(Integer.parseInt(stArray[2]));
							
						}
						else if ((e.getSource()==tempV2&&e.getDestination()==tempV)) {
							graph.getEdge(Integer.parseInt(stArray[1]), Integer.parseInt(stArray[0]),e.getLineId()).setDistance(Integer.parseInt(stArray[2]));
							
						}
					}
			    	
			    	
				}
		    	
		    			    	
		    }

		    


			  
		} catch (FileNotFoundException e) {
			System.out.println("Error detected while reading distance txt!!");
		} catch (IOException e) {
			System.out.println("Error detected while reading distance txt!!");
		}
		
	}

	public void readLine() {
		File lineTXT = new File("/Line.txt");		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(lineTXT));
			

		    String st; 
		    String[] stArray = new String[4];
		    st=br.readLine();
		    while ((st = br.readLine()) != null) {
		    	
		    	stArray=st.split(";");
		    	Line temp = new Line(Integer.parseInt(stArray[0]),Integer.parseInt(stArray[1]),stArray[2],Integer.parseInt(stArray[3]));
		    	graph.addLineList(temp);
		    	
		    }

		    


			  
		} catch (FileNotFoundException e) {
			System.out.println("Error detected while reading distance txt!!");
		} catch (IOException e) {
			System.out.println("Error detected while reading distance txt!!");
		}
	}

	public void readTrip() {
		File tripTXT = new File("/Trip.txt");		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(tripTXT));
			

		    String st; 
		    String[] stArray = new String[4];
		    st=br.readLine();
		    int tempId = 0;
		    int tempStop=0;
		    int count=1;
		    int tempDirection=-1;

		    while ((st = br.readLine()) != null) {
		    	stArray=st.split(";");

		    	if (tempId!=Integer.parseInt(stArray[0]) || tempDirection!=Integer.parseInt(stArray[1])) {
					tempId=Integer.parseInt(stArray[0]);
					tempDirection=Integer.parseInt(stArray[1]);
					count=1;
				}
		    	if (count==2) {

					Edge tmp = new Edge(Integer.parseInt(stArray[0]),graph.getVertex(tempStop),graph.getVertex(Integer.parseInt(stArray[3])),Integer.parseInt(stArray[1]),Integer.parseInt(stArray[2]));
					tempStop=Integer.parseInt(stArray[3]);
					graph.addEdge(tmp);
					tmp.getSource().addLine(tmp.getLineId()+"="+tmp.getDirection());
					tmp.getDestination().addLine(tmp.getLineId()+"="+tmp.getDirection());
		    	}
		    	if (count==1) {
					tempStop=Integer.parseInt(stArray[3]);
					count=2;
				}
		    	
		    }

		    


			  
		} catch (FileNotFoundException e) {
			System.out.println("Error detected while reading distance txt!!");
		} catch (IOException e) {
			System.out.println("Error detected while reading distance txt!!");
		}
	}
	


}
