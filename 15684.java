class Main {
	public static int N, M, H;
	public static int[] Limit;
	public static int[][] Map;
	public static int Result = -1;
	
	public static void Move(int depth) {
		boolean prog = true;
		for (int i = 1; i <= N; i++) {
			int col = i;
			for (int j = 1; j <= H; j++) {
				// Right
				if (Map[j][col] == 1) {
					col += 1;
					continue;
				}
				
				// Left
				if (Map[j][col] == 2) {
					col -= 1;
					continue;
				}
			}
			
			if (col == i) continue;
			else {
				prog = false;
				break;
			}
		}
		
		if (prog) {
			Result = depth;
		}
	}
	
	public static void DFS(int n, int depth) {
		if (depth == n) {
			Move(depth);
			return;
		}
		
		for (int i = 1; i <= H; i++) {
			for (int j = 1; j <= N; j++) {
				int x = i;
				int y = j;
				int ny = y + 1;
				
				// Check range
				if (x < 1 || x > H || y < 1 || y > N || ny < 1 || ny > N) continue;
				
				// Check limit
				if (Limit[y] > H || Limit[ny] > H) continue;
				
				// Check bridge
				if (Map[x][y] != 0 || Map[x][ny] != 0) continue;
				
				Map[x][y] = 1; 		// Right
				Map[x][ny] = 2; 	// Left
				Limit[y] += 1;
				Limit[ny] += 1;
				
				DFS(n, depth + 1);
				
				Map[x][y] = 0; 		// Initialize
				Map[x][ny] = 0; 	// Initialize
				Limit[y] -= 1;
				Limit[ny] -= 1;
				
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		H = sc.nextInt();
		
		Limit = new int[N + 1];
		Map = new int[H + 1][N + 1];
		
		// Set map
		for (int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			Map[a][b] = 1; 			// Right
			Map[a][b + 1] = 2;		// Left
			Limit[b] += 1;
			Limit[b + 1] += 1;
		}
		
		// DFS
		for (int i = 0; i <= 3; i++) {
			DFS(i, 0);
			if (Result != -1) break;
		}
		
		System.out.println(Result);
		sc.close();
		
	}
}