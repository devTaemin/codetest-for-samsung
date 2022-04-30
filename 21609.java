class Main {
	public static int N, M;
	public static int Score;
	public static int[][] Map;
	public static int[][] Copy;
	public static Queue<Location> GlobalQ;
	public static Queue<Location> LocalQ;
	public static boolean[][] Visited;
	
	// Standard block
	public static int Rainbow;
	public static int X, Y;
	
	// North, East, South, West
	public static int[] Dx = { -1, 0, 1, 0 };
	public static int[] Dy = { 0, 1, 0, -1 };
	
	public static void main(String[] args) {
		init();
		
		while (true) {
			BFS();
			if (GlobalQ.size() == 0) break;
			
			remove();
			gravity();
			rotate();
			gravity();
		}
		
		result();
		
	}
	
	public static void result() {
		System.out.println(Score);
	}
	
	public static void rotate() {
		Copy = new int[N + 1][N + 1];
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				Copy[N - j + 1][i] = Map[i][j];
			}
		}
		
		Map = Copy;
	}
	
	public static void print() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				System.out.print(Map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void gravity() {
		
		for (int col = 1; col <= N; col++) {
			for (int row = N; row >= 1; row--) {
				int x = row;
				int y = col;
				
				int value = Map[x][y];
				
				if (value == -1 || value == -2) continue;
				
				while (true) {
					int nx = x + 1;
					
					if (nx < 1 || nx > N) break;
					
					if (Map[nx][y] != -2) break;
					
					Map[nx][y] = Map[x][y];
					Map[x][y] = -2;
					
					x = nx;
				}
			}
		}
	}
	
	public static void remove() {
		int size = GlobalQ.size();
		
		while(!GlobalQ.isEmpty()) {
			Location l = GlobalQ.poll();
			int x = l.getX();
			int y = l.getY();
			
			// EMPTY
			Map[x][y] = -2;
		}
		
		calScore(size);
	}
	
	public static void calScore(int size) {
		Score += (size * size);
	}
	
	
	public static void BFS() {
		GlobalQ = new LinkedList<Location>();
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				
				if (Map[i][j] <= 0) continue;
				
				LocalQ = new LinkedList<Location>();
				Visited = new boolean[N + 1][N + 1];
				LocalQ.add(new Location(i, j));
				Visited[i][j] = true;
				int count = 1;
				int number = Map[i][j];
				
				
				while(!LocalQ.isEmpty()) {
					Location l = LocalQ.poll();
					int x = l.getX();
					int y = l.getY();
					
					for (int d = 0; d < 4; d++) {
						int nx = x + Dx[d];
						int ny = y + Dy[d];
						
						if (nx < 1 || nx > N || ny < 1 || ny > N) continue;
						
						if ((Map[nx][ny] == 0 || Map[nx][ny] == number) && !Visited[nx][ny]) {
							LocalQ.add(new Location(nx, ny));
							Visited[nx][ny] = true;
							count++;
						}
					}
				}
				
				
				if(count >= 2 && GlobalQ.size() < count) {
					int stdX = 0;
					int stdY = 0;
					int rainbow = 0;
					GlobalQ = new LinkedList<Location>();
					
					for (int row = 1; row <= N; row++) {
						for (int col = 1; col <= N; col++) {
							if (Visited[row][col]) {
								if (stdX == 0 && stdY == 0) {
									if (Map[row][col] != 0) {
										stdX = row;
										stdY = col;
									}
								}
								
								if (Map[row][col] == 0) rainbow++;
								
								GlobalQ.add(new Location(row, col));
							}
						}
					}
					
					X = stdX;
					Y = stdY;
					Rainbow = rainbow;
					continue;
				}
				
				if (count >= 2 && GlobalQ.size() == count) {
					int stdX = 0;
					int stdY = 0;
					int rainbow = 0;
					
					for (int row = 1; row <= N; row++) {
						for (int col = 1; col <= N; col++) {
							if (Visited[row][col]) {
								if (stdX == 0 && stdY == 0) {
									if (Map[row][col] != 0) {
										stdX = row;
										stdY = col;
									}
								}
								
								if (Map[row][col] == 0) rainbow++;
							}
						}
					}
					
					if (Rainbow > rainbow) {
						continue;
					}
					else if (Rainbow < rainbow) {
						GlobalQ = new LinkedList<Location>();
						for (int row = 1; row <= N; row++) {
							for (int col = 1; col <= N; col++) {
								if (Visited[row][col]) GlobalQ.add(new Location(row, col));
							}
						}
						
						X = stdX;
						Y = stdY;
						Rainbow = rainbow;
						continue;
					}
					else {
						if (X > stdX) {
							continue;
						}
						else if (X < stdX) {
							GlobalQ = new LinkedList<Location>();
							for (int row = 1; row <= N; row++) {
								for (int col = 1; col <= N; col++) {
									if (Visited[row][col]) GlobalQ.add(new Location(row, col));
								}
							}
							
							X = stdX;
							Y = stdY;
							Rainbow = rainbow;
							continue;
						}
						else {
							if (Y > stdY) {
								continue;
							}
							else {
								GlobalQ = new LinkedList<Location>();
								for (int row = 1; row <= N; row++) {
									for (int col = 1; col <= N; col++) {
										if (Visited[row][col]) GlobalQ.add(new Location(row, col));
									}
								}
								
								X = stdX;
								Y = stdY;
								Rainbow = rainbow;
							}
						}
					}
				}
			}
		}
		
		
	}
	
	public static void init() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		Map = new int[N + 1][N + 1];
		Score = 0;
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				Map[i][j] = sc.nextInt();
			}
		}
		
		sc.close();
	}
}

class Location {
	private int x;
	private int y;
	
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
}