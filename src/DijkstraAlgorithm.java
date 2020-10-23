import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class DijkstraAlgorithm {
		Graph graph = new Graph();
		ReadFile f = new ReadFile(graph);
		
		public DijkstraAlgorithm() {
			f.readStop();
			f.readLine();
			f.readTrip();
			f.readDistance();

		}
		public int shortestPath(Vertex start,Vertex end, int type, int size) {
			int resultCount;
			resetValues(graph);
			start.setValue(0);
			ArrayList<String> ar = new ArrayList<>();
			//////////////////////////////////////////////////////////////////////////////7
			
			
			// AKTARMA 
			
			
			
			

				
				for (String[] ss:start.getNeighborVertex()) { //BAŞLANGIÇ DURAĞI VE KOMŞU DURAKLARI TARAR
					
					start.setValue(0);
					Vertex tempStart=graph.getVertex(Integer.parseInt(ss[0]));
					graph.getVertex(Integer.parseInt(ss[0])).setValue(start.getValue());
					if (graph.getVertex(Integer.parseInt(ss[0]))!=start) {
						graph.getVertex(Integer.parseInt(ss[0])).setValue(Integer.parseInt(ss[1]));
					}
					for (String[] lines:graph.getVertex(Integer.parseInt(ss[0])).getLines()) { //DURAKTAN GİDEN LİNE LARI TARAR
						int line=Integer.parseInt(lines[0]);

						boolean flag = true;
						boolean flag2 = true;
						int direction2 = Integer.parseInt(lines[1]);
						Vertex tempV=graph.findDestination(graph.getVertex(Integer.parseInt(ss[0])), line, direction2,type);
						
						if (tempV==null) {
							flag2=false;
						}
						Vertex tempAktarma1 = tempV;
							while(flag&&flag2) { //DURAKTAN LİNELARIN SONUNA KADAR GİDER
							for (String[] ss2:tempV.getNeighborVertex()) {//ULAŞILAN İLK DURAĞIN KOMŞULARINI TARAR


								Vertex tempStop = graph.getVertex(Integer.parseInt(ss2[0]));
								
								if (tempV!=tempStop) {
									tempStop.setValue(tempV.getValue()+Integer.parseInt(ss2[1]));
								}

								Vertex tempAktarma2 = graph.getVertex(Integer.parseInt(ss2[0]));
								for (String[] ss3:end.getNeighborVertex()) {//SON DURAĞI VE KOMŞULARINI TARAR


									int tempLine=graph.lineCheck(tempStop, graph.getVertex(Integer.parseInt(ss3[0])));
									
									if (tempLine!=0) {

										int direction= graph.findDirection(tempStop, graph.getVertex(Integer.parseInt(ss3[0])), tempLine);

										boolean flag3=true;
										if (graph.findDestination(tempStop, tempLine,direction,type)!=null) {
											tempStop=graph.findDestination(tempStop, tempLine,direction,type);
										}
										else
											flag3=false;
										if (tempStop==null) {
											flag3=false;
										}
										Vertex tempLast = graph.getVertex(Integer.parseInt(ss3[0]));
										while(flag3) {//HEDEF DURAĞA ULAŞANA KADAR DÖNER
											tempLine=graph.lineCheck(tempAktarma2, tempLast);
											if (tempStop==graph.getVertex(Integer.parseInt(ss3[0]))) {
												if (tempStop!=end) {
													end.setValue(tempStop.getValue()+Integer.parseInt(ss3[1]));
												}
												
												int distance=0;
												int distance1=0,distance2=0;
												int walk1=0,walk2=0,walk3=0;
												int stopCounter=0,stopCounter1=0,stopCounter2=0;
												if (tempStart!=start) {
													
													walk1=Integer.parseInt(ss[1]);
													stopCounter++;
												}
												if (tempLast!=end) {
													walk3=Integer.parseInt(ss3[1]);
													stopCounter++;
												}
												if (line==tempLine && direction==direction2) {

													if (type==2) {
														distance=graph.calculateDistance(tempStart, tempLast, line, direction2,type);
													}
													
													stopCounter+=graph.stopCount(tempStart, tempLast, line, direction2);
												}
												else {
													if (tempAktarma1!=tempAktarma2) {
														walk2=Integer.parseInt(ss2[1]);
														stopCounter++;
													}
													if (type==2) {
														
												
														distance1 =graph.calculateDistance(tempStart, tempAktarma1, line, direction2,type);
														distance2 =graph.calculateDistance(tempAktarma2, tempLast, tempLine, direction,type);
													}													
													stopCounter1 =graph.stopCount(tempStart, tempAktarma1, line, direction2);													
													stopCounter2 =graph.stopCount(tempAktarma2, tempLast, tempLine, direction);
													
												}
												distance=distance+distance1+distance2+walk1+walk2+walk3;
												stopCounter+=stopCounter1+stopCounter2;
												
												if (walk2==-1) {
													walk2=0;
												}
												if (walk2==0) {
													stopCounter--;
												}
												if (type==1) {
													distance=5;
												}
												String tempString =distance+"-"+start.getStopId()+"-"+tempStart.getStopId()+"-"+walk1+"-"+line+"-"+stopCounter1+"-"+tempAktarma1.getStopId()+"-"+tempAktarma2.getStopId()+"-"+walk2+"-"+
														tempLine+"-"+stopCounter2+"-"+tempLast.getStopId()+"-"+end.getStopId()+"-"+walk3+"-"+stopCounter+"-"+direction2+"-"+direction;
												
													
													
												if (checkString(tempString, ar)) {
													ar.add(tempString);
												}
												
												break;	
												
											}


											if (graph.findDestination(tempStop, tempLine,direction,type)!=null) {
												tempStop=graph.findDestination(tempStop, tempLine,direction,type);
											}
											else {
												break;
											}
											if (tempStop==null) {
												break;
											}

										}
										
										
									}


								}
								
							}
							if (graph.findDestination(tempV, line, direction2,type)!=null) {
								tempV=graph.findDestination(tempV, line, direction2,type);
							}else
								flag=false;
							
							
							if (tempV==null) {

								flag=false;

							}

						}
										
					}

				}
		
			resultCount=printResults(sortArrayList(ar,10,type),size,type);
			return resultCount;
			
		}
		public boolean checkString(String s, ArrayList<String> ar) {
			
			String[] tempSt = s.split("-");
			if (ar.contains(s)) {
				return false;
			}
			else if (tempSt[4]==tempSt[9]) {
				for (String i:ar) {
					String[] tempSt2=i.split("-");
					if (tempSt2[4]==tempSt2[9]) {
						if (tempSt2[4]==tempSt[4]) {
							return false;
						}
					}
					
				}
			}
			return true;
			
		}
		public int printResults(String[] results, int n, int type) {//SONUÇLARI YAZDIRIR
			int resultCount=0;
			ArrayList<String> checkStrings= new ArrayList<>();
			for (int i = 0; i < n; i++) {
				boolean flag=true;
				if (results[i]==null) {
					break;
				}
				for (String s:checkStrings) {
					if (s==results[i]) {
						flag=false;
					}
				}
				checkStrings.add(results[i]);
				if (flag) {
					resultCount++;
					String[] temp=results[i].split("-");
					boolean walk1 =false;
					boolean walk2 =false;
					boolean walk3 =false;
					boolean aktarma =false;
					try {
						if (Integer.parseInt(temp[3])!=0) {
							walk1=true;
						}

						if (Integer.parseInt(temp[8])!=0) {
							walk2=true;
						}
						if (Integer.parseInt(temp[13])!=0) {
							walk3=true;
						}
						if (!temp[4].equals(temp[9])) {
							aktarma=true;
						}
						System.out.println();
						System.out.println("--------------");
						System.out.println();
						System.out.print("Path"+(i+1)+" : ");
						if (walk1) {
							System.out.print("Walk - ");
						}
						System.out.print(graph.getLine(Integer.parseInt(temp[4])).getLineNo());
						
						if (aktarma) {
							if (walk2) {
								System.out.print(" - Walk - "+graph.getLine(Integer.parseInt(temp[9])).getLineNo());
							}
							else
								System.out.print(" - "+graph.getLine(Integer.parseInt(temp[9])).getLineNo());
							
						}
						if (walk3) {
							System.out.print(" - Walk");
						}
						
						System.out.println();
						System.out.println();
						System.out.println("--------------");
					} catch (NumberFormatException e) {
						System.err.println("PrintResults: Girilen string değerinde eksik değer var");
					}
					
					
					if (aktarma) {
						if (walk1) {
							System.out.println("Line: Walk");
							System.out.println("Origin Stop: "+temp[1]+" - "+graph.getVertex(Integer.parseInt(temp[1])).getName());
							System.out.println("Destination Stop: "+temp[2]+" - "+graph.getVertex(Integer.parseInt(temp[2])).getName());
							System.out.println("Walk Distance: "+temp[3]+"m");
							System.out.println("--------------");
							
							
							System.out.println("Line: "+graph.getLine(Integer.parseInt(temp[4])).getLineNo()+"  "+graph.getLine(Integer.parseInt(temp[4])).getName()+" (Direction "+temp[15]+")");
							System.out.println("Origin Stop: "+temp[2]+" - "+graph.getVertex(Integer.parseInt(temp[2])).getName());
							System.out.println("Destination Stop: "+temp[6]+" - "+graph.getVertex(Integer.parseInt(temp[6])).getName());
							System.out.println("Stop Counter: "+temp[5]);
							System.out.println("--------------");
							
						}
						else {
							System.out.println("Line: "+graph.getLine(Integer.parseInt(temp[4])).getLineNo()+"  "+graph.getLine(Integer.parseInt(temp[4])).getName()+" (Direction "+temp[15]+")");
							System.out.println("Origin Stop: "+temp[1]+" - "+graph.getVertex(Integer.parseInt(temp[1])).getName());
							System.out.println("Destination Stop: "+temp[6]+" - "+graph.getVertex(Integer.parseInt(temp[6])).getName());
							System.out.println("Stop Counter: "+temp[5]);
							System.out.println("--------------");
						}
						if (walk2) {
							System.out.println("Line: Walk");
							System.out.println("Origin Stop: "+temp[6]+" - "+graph.getVertex(Integer.parseInt(temp[6])).getName());
							System.out.println("Destination Stop: "+temp[7]+" - "+graph.getVertex(Integer.parseInt(temp[7])).getName());
							System.out.println("Walk Distance: "+temp[8]+"m");
							System.out.println("--------------");
							
							
							System.out.println("Line: "+graph.getLine(Integer.parseInt(temp[9])).getLineNo()+"  "+graph.getLine(Integer.parseInt(temp[9])).getName()+" (Direction "+temp[16]+")");
							System.out.println("Origin Stop: "+temp[7]+" - "+graph.getVertex(Integer.parseInt(temp[7])).getName());
							System.out.println("Destination Stop: "+temp[11]+" - "+graph.getVertex(Integer.parseInt(temp[11])).getName());
							System.out.println("Stop Counter: "+temp[10]);
							System.out.println("--------------");
						}
						else {
							System.out.println("Line: "+graph.getLine(Integer.parseInt(temp[9])).getLineNo()+"  "+graph.getLine(Integer.parseInt(temp[9])).getName()+" (Direction "+temp[16]+")");
							System.out.println("Origin Stop: "+temp[6]+" - "+graph.getVertex(Integer.parseInt(temp[6])).getName());
							System.out.println("Destination Stop: "+temp[11]+" - "+graph.getVertex(Integer.parseInt(temp[11])).getName());
							System.out.println("Stop Counter: "+temp[10]);
							System.out.println("--------------");
						}
						if (walk3) {
							System.out.println("Line: Walk");
							System.out.println("Origin Stop: "+temp[11]+" - "+graph.getVertex(Integer.parseInt(temp[11])).getName());
							System.out.println("Destination Stop: "+temp[12]+" - "+graph.getVertex(Integer.parseInt(temp[12])).getName());
							System.out.println("Walk Distance: "+temp[13]+"m");
							System.out.println("--------------");
						}
						
						if (type==2) {
							System.out.println("Total Distance: "+temp[0]+"m");
						}
						else {
							System.out.println("Total Stop Counter: "+temp[14]);
						}
						
					}
					else {
						if (walk1) {
							System.out.println("Line: Walk");
							System.out.println("Origin Stop: "+temp[1]+" - "+graph.getVertex(Integer.parseInt(temp[1])).getName());
							System.out.println("Destination Stop: "+temp[2]+" - "+graph.getVertex(Integer.parseInt(temp[2])).getName());
							System.out.println("Walk Distance: "+temp[3]+"m");
							System.out.println("--------------");
							
							
							System.out.println("Line: "+graph.getLine(Integer.parseInt(temp[4])).getLineNo()+"  "+graph.getLine(Integer.parseInt(temp[4])).getName()+" (Direction "+temp[15]+")");
							System.out.println("Origin Stop: "+temp[2]+" - "+graph.getVertex(Integer.parseInt(temp[2])).getName());
							System.out.println("Destination Stop: "+temp[11]+" - "+graph.getVertex(Integer.parseInt(temp[11])).getName());
							System.out.println("Stop Counter: "+temp[14]);
							System.out.println("--------------");
							
							if (walk3) {
								System.out.println("Line: Walk");
								System.out.println("Origin Stop: "+temp[11]+" - "+graph.getVertex(Integer.parseInt(temp[11])).getName());
								System.out.println("Destination Stop: "+temp[12]+" - "+graph.getVertex(Integer.parseInt(temp[12])).getName());
								System.out.println("Walk Distance: "+temp[13]+"m");
								System.out.println("--------------");
							}
							
						}
						else {
							System.out.println("Line: "+graph.getLine(Integer.parseInt(temp[4])).getLineNo()+"  "+graph.getLine(Integer.parseInt(temp[4])).getName()+" (Direction "+temp[15]+")");
							System.out.println("Origin Stop: "+temp[1]+" - "+graph.getVertex(Integer.parseInt(temp[1])).getName());
							System.out.println("Destination Stop: "+temp[11]+" - "+graph.getVertex(Integer.parseInt(temp[11])).getName());
							System.out.println("Stop Counter: "+temp[14]);
							System.out.println("--------------");
							if (walk3) {
								System.out.println("Line: Walk");
								System.out.println("Origin Stop: "+temp[11]+" - "+graph.getVertex(Integer.parseInt(temp[11])).getName());
								System.out.println("Destination Stop: "+temp[12]+" - "+graph.getVertex(Integer.parseInt(temp[12])).getName());
								System.out.println("Walk Distance: "+temp[13]+"m");
								System.out.println("--------------");
							}
						}
						
						if (type==2) {
							System.out.println("Total Distance: "+temp[0]+"m");
						}
						else {
							System.out.println("Total Stop Counter: "+temp[14]);
						}
					}
					
				}
				
				
				
				
				
				
				
			}
			if (resultCount==0) {
				System.out.println();
				System.out.println("---------------");
				System.out.println("No proper line found");
				System.out.println("---------------");
				System.out.println();
			}
			return resultCount;
		}
		public String[] sortArrayList(ArrayList<String> ar, int size, int type) {//ARRAYLİSTTEKİ STRINGLERİ İSTENİLEN TYPE'A GÖRE SIRALAR
			String[] tempSt = new String[size];
			int temp=0;
			if (type==1) {
				temp=14;
			}
			else
				temp=0;
			for (String s:ar) {
				
				for (int i = 0; i < tempSt.length; i++) {
					if (tempSt[i]==null) {
						tempSt[i]=s;
						break;
					}
					String[] split=tempSt[i].split("-");
					int element = Integer.parseInt(split[temp]);
					String[] split2=s.split("-");
					int scan = Integer.parseInt(split2[temp]);
					
					
					if (scan<element) {
						String tempString = tempSt[i];
						tempSt[i]=s;
						for (int j = i+1; j < tempSt.length; j++) {
							String tempString2 = tempSt[j];
							tempSt[j]=tempString;
							tempString=tempString2;
						}
						break;
					}
				}
				
				
			}
			return tempSt;
			
			
		}
		public void resetValues(Graph g) {//BÜTÜN VERTEXLERİN VALUELARINI MAXIMUM YAPAR
			for (Vertex v : g.vertices()) {
				v.setValue(Integer.MAX_VALUE);
			}
		}
		
}