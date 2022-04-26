class Fish {
	private int x;
	private int y;
	
	public Fish(int x, int y) {
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

class Main {
	public static int N, K, Diff;
	public static int[][] Map = new int[1001][1001];
	public static int[][] CopyMap;
	public static boolean[][] Visited;
	public static int Start, End, Floor;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		
		for (int i = 1; i <= N; i++) {
			Map[1][i] = sc.nextInt();
		}
		
		Diff = (int)1e9;
		int count = 0;
		
		while(true) {
			//Check();
			//if (Diff <= K) break;
			
			count++;
			System.out.println();
			System.out.print("Count: " + count);
			System.out.println("--------------------------------------------");
			AddFish();
			Print();
			
			PutBowl();
			Print();
			
			Start = 2;
			End = 2;
			RotateBowl();	
			Print();
			
			ArrangeBowl();	
			Print();
			
			RevolveBowl();
			Print();
			
			LineUp();
			Print();
			
			Fold();
			Print();
			ArrangeBowl();
			Print();
			
			LineUp();
			Print();
			System.out.println("--------------------------------------------");
			System.out.println();
			
			//?
			Check();
			if (Diff <= K) break;
		}
		
		System.out.println(count);
		sc.close();
	}
	
	public static void Print() {
		for (int i = N * 2; i >= 1; i--) {
			for (int j = 1; j <= N * 2; j++) {
				System.out.print(Map[i][j] + "  ");
			}
			System.out.println();
		}
		
		System.out.println();
	}
	
	public static void AddFish() {
		int min = (int)1e9;
		for (int i = 1; i <= N; i++) {
			min = (min < Map[1][i]) ? min : Map[1][i];
		}
		
		for (int i = 1; i <= N; i++) {
			if (Map[1][i] == min) Map[1][i]++;
		}
	}
	
	public static void PutBowl() {
		int target = Map[1][1];
		Map[1][1] = 0;
		Map[2][2] = target;
	}
	
	public static void RotateBowl() {
		while (true) {
			Floor = 2;			
			
			// Find top floor
			while (true) {
				if (Map[Floor][End] > 0) {
					Floor += 1;
				}
				else {
					Floor -= 1;
					break;
				}
			}
			
			while (true) {
				// Condition: Rotate Pause
				if (Map[1][End] == 0) return;
				
				if (Map[Floor][End] > 0) {
					End++;
				}
				else {
					break;
				}
			}
			
			// Rotate
			int x = 1;
			int y = End;
				
			for (int i = Start; i < End; i++) {
				for (int j = 1; j <= Floor; j++) {
					int gap = Math.abs(i - End);
					Map[x + gap][y + (j - 1)] = Map[j][i];
					Map[j][i] = 0;
				}
			}
			
			Start = End;
		}
	}
	
	public static void ArrangeBowl() {
		CopyMap = new int[1001][1001];
		Visited = new boolean[1001][1001];
		Queue<Fish> queue = new LinkedList<Fish>();
		queue.add(new Fish(1, Start));
		
		while(!queue.isEmpty()) {
			Fish f = queue.poll();
			int x = f.getX();
			int y = f.getY();
			
			if (Visited[x][y] == true) continue;
			
			// Up
			int ux = x + 1;
			int uy = y;
			
			if (Map[Floor][End] > 0) {
				if (ux <= Floor && uy <= End && !(ux == 1 && uy == End)) {
					if (Map[ux][uy] != 0) {
						
						int gap = Math.abs(Map[x][y] - Map[ux][uy]);
						gap /= 5;
						
						if (Map[x][y] < Map[ux][uy]) {
							CopyMap[x][y] += gap;
							CopyMap[ux][uy] -= gap;
						}
						else {
							CopyMap[x][y] -= gap;
							CopyMap[ux][uy] += gap;
						}
						
						queue.add(new Fish(ux, uy));
						
					}
					
				}
				
				// Right
				int rx = x;
				int ry = y + 1;
				
				if (rx <= Floor && ry <= End && !(rx == 1 && ry == End)) {
					if (Map[rx][ry] != 0) {
						
						int gap = Math.abs(Map[x][y] - Map[rx][ry]);
						gap /= 5;
						
						if (Map[x][y] < Map[rx][ry]) {
							CopyMap[x][y] += gap;
							CopyMap[rx][ry] -= gap;
						}
						else {
							CopyMap[x][y] -= gap;
							CopyMap[rx][ry] += gap;
						}
						
						queue.add(new Fish(rx, ry));
						
					}
				}
			}
			else {
				if (ux <= Floor && uy < End && !(ux == 1 && uy == End)) {
					if (Map[ux][uy] != 0) {
						
						int gap = Math.abs(Map[x][y] - Map[ux][uy]);
						gap /= 5;
						
						if (Map[x][y] < Map[ux][uy]) {
							CopyMap[x][y] += gap;
							CopyMap[ux][uy] -= gap;
						}
						else {
							CopyMap[x][y] -= gap;
							CopyMap[ux][uy] += gap;
						}
						
						queue.add(new Fish(ux, uy));
						
					}
					
				}
				
				// Right
				int rx = x;
				int ry = y + 1;
				
				if (rx <= Floor && ry < End && !(rx == 1 && ry == End)) {
					if (Map[rx][ry] != 0) {
						
						int gap = Math.abs(Map[x][y] - Map[rx][ry]);
						gap /= 5;
						
						if (Map[x][y] < Map[rx][ry]) {
							CopyMap[x][y] += gap;
							CopyMap[rx][ry] -= gap;
						}
						else {
							CopyMap[x][y] -= gap;
							CopyMap[rx][ry] += gap;
						}
						
						queue.add(new Fish(rx, ry));
						
					}
					
				}
			}
			
			
			
			
			
			Visited[x][y] = true;
		}
		
		for (int i = 1; i <= Floor; i++) {
			for (int j = Start; j <= End; j++) {
				Map[i][j] += CopyMap[i][j];
			}
		}
	}
	
	public static void RevolveBowl() {
		int x = 1;
		int sy = Start;
		int ey = End;
		int h = Floor;
		
		for (int row = h; row >= 2; row--) {
			for (int col = sy; col <= ey; col++) {
				int gap = Math.abs(sy - col);
				Map[x + gap][sy - (row - 1)] = Map[row][col];
				Map[row][col] = 0;
			}
		}
		
		End = Start - 1;
		Start = Start - (h - 1);
		Floor = 1;
		
		while(true) {
			if (Map[Floor][Start] > 0) Floor++;
			else {
				Floor--;
				break;
			}
		}
	}
	
	public static void LineUp() {
		int row = 1;
		int col = Start;
		
		for (int i = 1; i <= End; i++) {
			Map[1][i] = Map[row][col];
			Map[row][col] = 0;
			
			row++;
			if (row > Floor) {
				row = 1;
				col+= 1;
			}
		}
		
		Start = 1;
		End = N;
		Floor = 1;
	}
	
	public static void Fold() {
		
		for (int fold = 0; fold < 2; fold++) {
			int col = ((Start + End - 1) / 2) + 1;
			int floor = Floor;
			
			for (int r = 1; r <= floor; r++) {
				for (int c = 1; c < col; c++) {
					Map[floor + (floor - r + 1)][col + (col - c - 1)] = Map[r][c];
					Map[r][c] = 0;
				}
			}
			
			Start = col;
			Floor = 1;
			while(true) {
				if (Map[Floor][Start] > 0) Floor++;
				else {
					Floor--;
					break;
				}
			}
		}
	}
	
	public static void Check() {
		int min = (int)1e9;
		int max = -(int)1e9;
		
		for (int i = 1; i <= N; i++) {
			min = (min < Map[1][i]) ? min : Map[1][i];
			max = (max < Map[1][i]) ? Map[1][i] : max;
		}
		
		Diff = max - min;
	}
	
}

