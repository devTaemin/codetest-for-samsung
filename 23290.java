class Main {
	public static int M, S;
	public static Queue<Fish> Current = new LinkedList<Fish>();
	public static Queue<Fish> Next;
	public static int X, Y;				// Location of Shark
	public static int[][] Map;
	public static int[][] Smell;
	
	// Movement of Fish
	public static int[] Fx = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
	public static int[] Fy = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
	
	// Movement of Shark
	public static int[] Sx = { -1, 0, 1, 0 };
	public static int[] Sy = { 0, -1, 0, 1 };
	public static int[] SharkMove;
	public static int[][] DeadZone;
	public static int NumOfEatFish;
	
	// Find first matched range
	public static int Matched;
	
	
	
	public static void DFS(int n, int depth) {
		if (depth == n) {
			int x = X;
			int y = Y;
			
			// Check Range
			for (int i = 0; i < 3; i++) {
				int nx = x + Sx[SharkMove[i]];
				int ny = y + Sy[SharkMove[i]];
				
				if (nx < 1 || nx > 4 || ny < 1 || ny > 4) return;
				
				x = nx;
				y = ny;
			}
			
			Matched++;
			
			// Calculate the number of fish on the route
			// Copy Map;
			int[][] copy = new int[5][5];
			for (int i = 1; i <= 4; i++) {
				for (int j = 1; j <= 4; j++) {
					copy[i][j] = Map[i][j];
				}
			}
			x = X;
			y = Y;
			
			int count = 0;
			for (int i = 0; i < 3; i++) {
				int nx = x + Sx[SharkMove[i]];
				int ny = y + Sy[SharkMove[i]];
				
				count += copy[nx][ny];
				copy[nx][ny] = 0;
				
				x = nx;
				y = ny;
			}
			
			// Result
			if (NumOfEatFish < count || (Matched == 1)) {
				NumOfEatFish = count;
				x = X;
				y = Y;
				for (int i = 0; i < 3; i++) {
					int nx = x + Sx[SharkMove[i]];
					int ny = y + Sy[SharkMove[i]];
					
					DeadZone[i][0] = nx;
					DeadZone[i][1] = ny;
					
					x = nx;
					y = ny;
					
				}
			}
			
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			SharkMove[depth] = i;
			DFS(n, depth + 1);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		M = sc.nextInt();
		S = sc.nextInt();
		Map = new int[5][5];
		Smell = new int[5][5];
		
		for (int i = 0; i < M; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			int d = sc.nextInt();
			
			Current.add(new Fish(x, y, d));
			Map[x][y] += 1;
		}
		
		// Location of Shark
		X = sc.nextInt();
		Y = sc.nextInt();
		
		// Magic Show
		for (int magic = 0; magic < S; magic++) {
			
			// First, Copy current fish
			Next = new LinkedList<Fish>();
			int size = Current.size();
			for (int i = 0; i < size; i++) {
				Fish f = Current.poll();
				Next.add(new Fish(f.getX(), f.getY(), f.getD()));
				Current.add(f);
			}
			
			// Second, Move Fish
			size = Next.size();
			for (int i = 0; i < size; i++) {
				Fish f = Next.poll();
				int numOfRotate = 0;
				int x = f.getX();
				int y = f.getY();
				int d = f.getD();
				Map[x][y] -= 1;
				
				while(true) {
					
					if (numOfRotate == 8) break;
					int nx = x + Fx[d];
					int ny = y + Fy[d];
					
					// Check Range
					if (nx < 1 || nx > 4 || ny < 1 || ny > 4) {
						numOfRotate++;
						d = (d - 1 == 0) ? 8 : d - 1;
						continue;
					}
					
					// Check Smell
					if (Smell[nx][ny] > 0) {
						numOfRotate++;
						d = (d - 1 == 0) ? 8 : d - 1;
						continue;
					}
					
					// Check Shark
					if (nx == X && ny == Y) {
						numOfRotate++;
						d = (d - 1 == 0) ? 8 : d - 1;
						continue;
					}
					
					
					x = nx;
					y = ny;
					break;
				}
				
				Next.add(new Fish(x, y, d));
				Map[x][y] += 1;
			}
			
			// Third, Find Shark Route and Move Shark
			SharkMove = new int[3];
			DeadZone = new int[3][2];
			NumOfEatFish = 0;
			Matched = 0;
			DFS(3, 0);
			
			// Adopt DeadZone with moved fish
			size = Next.size();
			for (int i = 0; i < size; i++) {
				Fish f = Next.poll();
				int x = f.getX();
				int y = f.getY();
				boolean dead = false;
				
				for (int j = 0; j < 3; j++) {
					int sx = DeadZone[j][0];
					int sy = DeadZone[j][1];
					
					if (x == sx && y == sy) {
						dead = true;
						break;
					}
				}
				
				if (dead) {
					Map[x][y] -= 1;
					Smell[x][y] = 3;
				}
				else {
					Next.add(f);
				}
			}
			
			// Current Shark Location
			X = DeadZone[2][0];
			Y = DeadZone[2][1];
			
			// Paste Fish
			size = Current.size();
			for (int i = 0; i < size; i++) {
				Fish f = Current.poll();
				Map[f.getX()][f.getY()] += 1;
				Current.add(f);
			}
			
			size = Next.size();
			for (int i = 0; i < size; i++) {
				Fish f = Next.poll();
				Current.add(f);
			}
			
			// Diminish Smell
			for (int i = 1; i <= 4; i++) {
				for (int j = 1; j <= 4; j++) {
					if (Smell[i][j] > 0) Smell[i][j] -= 1;
				}
			}
		}
		
		// Result
		int count = 0;
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 4; j++) {
				count += Map[i][j];
			}
		}
		
		System.out.println(count);
		sc.close();
		
		
		
	}
}

class Fish {
	private int x;
	private int y;
	private int d;
	
	public Fish(int x, int y, int d) {
		this.x = x;
		this.y = y;
		this.d = d;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getD() {
		return this.d;
	}
	
}