class Main {
	public static int N, M, x, y, K;
	public static int[][] map;
	public static int[] command;
	public static int[] dice;
	public static int ceil;
	public static int floor;
	
	// East West North South
	public static int[] dx = { 0, 0, 0, -1, 1 };
	public static int[] dy = { 0, 1, -1, 0, 0 };
	
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException {
		
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		command = new int[K];
		dice = new int[6];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			command[i] = Integer.parseInt(st.nextToken());
		}
		
		Run();	
		bw.flush();
		bw.close();
	}
	
	public static void Run() throws IOException {
		int curX = x;
		int curY = y;
		
		for (int i = 0; i < K; i++) {
			int direction = command[i];
			int nx = curX + dx[direction];
			int ny = curY + dy[direction];
			
			if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
			
			curX = nx;
			curY = ny;
			
			flipDice(direction);
			ceil = dice[0];
			floor = dice[5];
			
			if (map[curX][curY] == 0) {
				map[curX][curY] = floor;
			}
			else {
				dice[5] = map[curX][curY];
				map[curX][curY] = 0;
			}

			
			bw.write(ceil + "\n");
						
		}
	}
	
	public static void flipDice(int direction) {
		
		int top = dice[0];
		int left = dice[1];
		int right = dice[2];
		int up = dice[3];
		int down = dice[4];
		int bottom = dice[5];
		
		if (direction == 1) {
			dice[0] = left;
			dice[1] = bottom;
			dice[2] = top;
			dice[3] = up;
			dice[4] = down;
			dice[5] = right;
		}
		
		else if (direction == 2) {
			dice[0] = right;
			dice[1] = top;
			dice[2] = bottom;
			dice[3] = up;
			dice[4] = down;
			dice[5] = left;
		}
		
		else if (direction == 3) {
			dice[0] = down;
			dice[1] = left;
			dice[2] = right;
			dice[3] = top;
			dice[4] = bottom;
			dice[5] = up;
		}
		
		// direction == 4
		else {
			dice[0] = up;
			dice[1] = left;
			dice[2] = right;
			dice[3] = bottom;
			dice[4] = top;
			dice[5] = down;
		}	
	}
	
}