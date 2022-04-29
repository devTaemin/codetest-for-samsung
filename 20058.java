class Block {
	private int x;
	private int y;
	
	public Block(int x, int y) {
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
	public static int N, Q, Count, Result;
	public static int[][] Map;
	public static int[][] Copy;
	public static int[][] Check;
	public static boolean[][] Visited;
	public static int[] L;
	
	// North, East, South, West
	public static int[] Dx = { -1, 0, 1, 0 };
	public static int[] Dy = { 0, 1, 0, -1 };
	
	public static void main(String[] args) {
		init();
		magic();
		count();
		BFS();
	}
	
	public static void BFS() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				
				Queue<Block> queue = new LinkedList<Block>();
				Visited = new boolean[N + 1][N + 1];
				
				if (Map[i][j] < 1) continue;
				
				queue.add(new Block(i, j));
				Visited[i][j] = true;
				int traverse = 1;
				
				while (!queue.isEmpty()) {
					Block block = queue.poll();
					int x = block.getX();
					int y = block.getY();
					
					for (int k = 0; k < 4; k++) {
						int nx = x + Dx[k];
						int ny = y + Dy[k];
						
						if (nx < 1 || nx > N || ny < 1 || ny > N) continue;
						
						if (Map[nx][ny] > 0 && !Visited[nx][ny]) {
							queue.add(new Block(nx, ny));
							Visited[nx][ny] = true;
							traverse += 1;
						}
					}
				}
				
				Result = Math.max(Result, traverse);
				
			}
		}
		
		System.out.println(Result);
	}
	
	public static void count() {
		Count = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				Count += Map[i][j];
			}
		}
		
		System.out.println(Count);
	}
	
	public static void magic() {
		for (int magic = 1; magic <= Q; magic++) {
			int grid = L[magic];
			Copy = new int[N + 1][N + 1];
			Check = new int[N + 1][N + 1];
			
			// Rotate
			if (grid > 1) {
				for (int row = 1; row < N; row += grid) {
					for (int col = 1; col < N; col += grid) {
						rotate(row, col, grid);
					}
				}
				
				Map = Copy;
			}
			
			// Check
			check();
		}
	}
	public static void check() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				int x = i;
				int y = j;
				int count = 0;
				
				for (int k = 0; k < 4; k++) {
					int nx = x + Dx[k];
					int ny = y + Dy[k];
					
					if (nx < 1 || nx > N || ny < 1 || ny > N) continue;
					
					if (Map[nx][ny] > 0) count++;
				}
				
				if (count < 3) {
					Check[x][y] = -1;
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (Map[i][j] > 0) {
					Map[i][j] += Check[i][j];
				}
			}
		}
	}
	
	public static void rotate(int row, int col, int grid) {
		for (int i = 0; i < grid; i++) {
			for (int j = 0; j < grid; j++) {
				Copy[row + j][col + grid - i - 1] = Map[row + i][col + j];
			}
		}
	}
	
	public static void init() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		Q = sc.nextInt();
		
		N = pow(N);
		
		// Range 1 ~ N
		Map = new int[N + 1][N + 1];
		// Range 1 ~ Q
		L = new int[Q + 1];
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				Map[i][j] = sc.nextInt();
			}
		}
		
		for (int i = 1; i <= Q; i++) {
			int num = sc.nextInt();
			L[i] = pow(num);
		}
		
		sc.close();
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
	
	public static int pow(double n) {
		double result = Math.pow(2, n);
		return (int)result;
	}
}