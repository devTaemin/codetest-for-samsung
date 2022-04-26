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

class Main {
	public static int N, M, K;
	public static int[][] Map;
	public static boolean[][] Visited;
	
	// East South West North
	public static int[] Dx = { 0, 1, 0, -1 };
	public static int[] Dy = { 1, 0, -1, 0 };
	
	// Index
	public static int X, Y, D;
	
	// Dice [ Top, Left, Right, Up, Down, Bottom ]
	public static int[] Dice = new int[] { 1, 4, 3, 2, 5, 6 };
	
	// Store Value
	public static int Count, Score;
	public static int A, B;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		
		Map = new int[N + 1][M + 1]; // [1 ~ N] [1 ~ M]
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				Map[i][j] = sc.nextInt();
			}
		}
		
		// Initialize Index
		X = 1;
		Y = 1;
		D = 0;
		Score = 0;
		
		Run();
		System.out.println(Score);
		sc.close();
		
	}
	
	public static int Backward() {
		int result = 0;
		
		// East
		if (D == 0) result = 2;
		
		// South
		if (D == 1) result = 3;
		
		// West
		if (D == 2) result = 0;
		
		// North
		if (D == 3) result = 1;
		
		return result;
	}
	
	public static void BFS() {
		Queue<Location> queue = new LinkedList<Location>();
		Visited = new boolean[N + 1][M + 1];
		queue.add(new Location(X, Y));
		Visited[X][Y] = true;
		
		while(!queue.isEmpty()) {
			Location location = queue.poll();
			int x = location.getX();
			int y = location.getY();
			Count++;
			
			// East South West North
			for (int i = 0; i < 4; i++) {
				int nx = x + Dx[i];
				int ny = y + Dy[i];
				
				// Range
				if (nx < 1 || nx > N || ny < 1 || ny > M) continue;
				
				if (Map[nx][ny] == B && !Visited[nx][ny]) {
					Visited[nx][ny] = true;
					queue.add(new Location(nx, ny));
				}
			}
		}
	}
	
	public static void Run() {
		
		for (int run = 0; run < K; run++) {
			// First, Check movement
			int nx = X + Dx[D];
			int ny = Y + Dy[D];
			
			if (nx < 1 || nx > N || ny < 1 || ny > M) {
				D = Backward();
				nx = X + Dx[D];
				ny = Y + Dy[D];
				
			}
			
			X = nx;
			Y = ny;
			
			// Second, Role Dice
			RollDice();
			
			// Third, Calculate Score
			A = Dice[5];
			B = Map[X][Y];
			Count = 0;
			BFS();
			Score += B * Count;
			
			// Fourth, Compare value to check Direction
			Check();
		}
		
	}
	
	public static void Clock() {
		if (D + 1 > 3) D = 0;
		else D += 1;
	}
	
	public static void ReverseClock() {
		if (D - 1 < 0) D = 3;
		else D -= 1;
	}
	
	public static void Check() {
		if (A > B) Clock();
		
		if (A < B) ReverseClock();
	}
	
	public static void RollDice() {
		int t = Dice[0];
		int l = Dice[1];
		int r = Dice[2];
		int u = Dice[3];
		int d = Dice[4];
		int b = Dice[5];
		
		// East
		if (D == 0) {
			Dice[0] = l;
			Dice[1] = b;
			Dice[2] = t;
			Dice[3] = u;
			Dice[4] = d;
			Dice[5] = r;
		}
		
		// South
		if (D == 1) {
			Dice[0] = u;
			Dice[1] = l;
			Dice[2] = r;
			Dice[3] = b;
			Dice[4] = t;
			Dice[5] = d;
		}
		
		// West
		if (D == 2) {
			Dice[0] = r;
			Dice[1] = t;
			Dice[2] = b;
			Dice[3] = u;
			Dice[4] = d;
			Dice[5] = l;
		}
		
		// North
		if (D == 3) {
			Dice[0] = d;
			Dice[1] = l;
			Dice[2] = r;
			Dice[3] = t;
			Dice[4] = b;
			Dice[5] = u;
		}
	}
	
}