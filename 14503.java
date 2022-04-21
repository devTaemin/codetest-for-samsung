class Main {
	public static int N, M, r, c, d;
	public static int[][] map;
	public static int count;
	
	// North(0), East(1), South(2), West(3)
	public static int[] dx = { -1, 0, 1, 0 };
	public static int[] dy = { 0, 1, 0, -1 };
	
	// Backward: South, West, North, East
	public static int[] bx = { 1, 0, -1, 0 };
	public static int[] by = { 0, -1, 0, 1 };
	
	public static void Count() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 2) count++;
			}
		}
	}
	
	public static int Rotate(int direction) {
		if (direction == 0) return 3;
		else return direction - 1;
	}
	
	public static void Run() {
		int x = r;
		int y = c;
		int turn = d;
		int numOfTurn = 0;
		
		while (true) {
			// Clean room
			map[x][y] = 2;
			
			// Check Backward
			if (numOfTurn == 4) {
				int rx = x + bx[turn];
				int ry = y + by[turn];
										
				if (rx < 0 || rx >= N || ry < 0 || ry >= M) break;
										
				if (map[rx][ry] == 1) break;
										
				x = rx;
				y = ry;
				numOfTurn = 0;
				continue;
			}
			
			// Rotate direction
			turn = Rotate(turn);
			numOfTurn++;
			
			// Next index
			int nx = x + dx[turn];
			int ny = y + dy[turn];
			
			// Check range
			if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
			
			// Check room
			if (map[nx][ny] == 1 || map[nx][ny] == 2) continue;
			
			// Move
			x = nx;
			y = ny;
			numOfTurn = 0;			
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		r = sc.nextInt();
		c = sc.nextInt();
		d = sc.nextInt();
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		Run();
		Count();
		System.out.println(count);
	}
}