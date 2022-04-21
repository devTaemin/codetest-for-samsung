class Main {
	public static int N, M;
	public static int[][] map;
	public static int max;
	
	// North, East, South, West
	public static int[] dx = { -1, 0, 1, 0 };
	public static int[] dy = { 0, 1, 0, -1 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean[][] visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = true;
				Find(1, 4, i, j, map[i][j], visited);
				visited[i][j] = false;
				Cross(i, j);
			}
		}
		
		bw.write(max + "\n");
		bw.flush();
		bw.close();
	}
	
	public static boolean checkRange(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= M) return false;
		return true;
	}
	
	public static void Cross(int x, int y) {
		int[] up = { x - 1, y };
		int[] left = { x, y - 1 };
		int[] right = { x, y + 1 };
		int[] down = { x + 1, y };
		
		// ㅗ
		if (checkRange(up[0], up[1]) &&
			checkRange(left[0], left[1]) &&
			checkRange(right[0], right[1])) {
			int temp = map[up[0]][up[1]] + map[left[0]][left[1]] + map[right[0]][right[1]] + map[x][y];
			max = (max < temp) ? temp : max;
		}
		
		// ㅏ
		if (checkRange(up[0], up[1]) &&
			checkRange(down[0], down[1]) &&
			checkRange(right[0], right[1])) {
			int temp = map[up[0]][up[1]] + map[down[0]][down[1]] + map[right[0]][right[1]] + map[x][y];
			max = (max < temp) ? temp : max;
		}
		
		// ㅜ
		if (checkRange(left[0], left[1]) &&
			checkRange(down[0], down[1]) &&
			checkRange(right[0], right[1])) {
			int temp = map[left[0]][left[1]] + map[down[0]][down[1]] + map[right[0]][right[1]] + map[x][y];
			max = (max < temp) ? temp : max;
		}
		
		// ㅓ
		if (checkRange(left[0], left[1]) &&
			checkRange(down[0], down[1]) &&
			checkRange(up[0], up[1])) {
			int temp = map[left[0]][left[1]] + map[down[0]][down[1]] + map[up[0]][up[1]] + map[x][y];
			max = (max < temp) ? temp : max;
		}
	}
	
	public static void Find(int depth, int target, int x, int y, int count, boolean[][] visited) {
		
		if (depth == target) {
			max = Math.max(count, max);			
			return;
		}
		
		// North, East, South, West
		for (int j = 0; j < 4; j++) {
			int nx = x + dx[j];
			int ny = y + dy[j];
					
			if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
					
			if (!visited[nx][ny]) {
				visited[nx][ny] = true;
				Find(depth + 1, target, nx, ny, count + map[nx][ny], visited);
				visited[nx][ny] = false;
			}
		}
		
		return;
	}
	
}