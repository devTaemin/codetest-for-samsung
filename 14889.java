class Main {
	public static int N;
	public static int[][] map;
	public static int[] teamA, teamB;
	public static boolean[] visited;
	public static int gap = (int)1e9;
	
	public static int Calculate(int[] team) {
		int total = 0;
		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < N / 2; j++) {
				total += map[team[i]][team[j]];
			}
		}
		
		return total;
	}
	
	public static void DFS(int target, int start, int depth) {
		if (depth == target) {
			int index = 0;
			for (int i = 1; i <= N; i++) {
				if (visited[i] == false) {
					teamB[index++] = i;
				}
			}
			
			int a = Calculate(teamA);
			int b = Calculate(teamB);
			int result = Math.abs(a - b);
			gap = (gap < result) ? gap : result;
			
			return;
		}
		
		for (int i = start; i <= N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				teamA[depth] = i;
				DFS(target, i + 1, depth + 1);
				visited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N + 1][N + 1];
		visited = new boolean[N + 1];
		teamA = new int[N / 2];
		teamB = new int[N / 2];
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		DFS(N/2, 1, 0);
		System.out.println(gap);
	}
}