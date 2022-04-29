class Main {
	public static int N, M, Result;
	public static int[] D, S;
	public static int[][] Map;
	public static int[][] Check;
	public static boolean[][] Visited;
	public static Queue<Cloud> Queue = new LinkedList<Cloud>();
	
	// Direction
	public static int[] Dx = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
	public static int[] Dy = { 0, -1, -1, 0, 1, 1, 1, 0, -1 }; 
	
	// Diagonal
	public static int[] Cx = { -1, -1, 1, 1 };
	public static int[] Cy = { -1, 1, -1, 1 };
	
	public static void main(String[] args) {
		init();
		run();
		result();
	}
	
	public static void result() {
		Result = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				Result += Map[i][j];
			}
		}
		
		System.out.println(Result);
	}
	
	public static void run() {
		
		for (int m = 1; m <= M; m++) {
			
			move(m);
			rainy();
			createCloud();
			
		}
		
	}
	
	public static void createCloud() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (Map[i][j] >= 2 && !Visited[i][j]) {
					Map[i][j] -= 2;
					Queue.add(new Cloud(i, j));
				}
			}
		}
	}
	
	public static void rainy() {
		Check = new int[N + 1][N + 1];
		Visited = new boolean[N + 1][N + 1];
		int size = Queue.size();
		
		for (int i = 1; i <= size; i++) {
			Cloud cloud = Queue.poll();
			int x = cloud.getX();
			int y = cloud.getY();
			
			Visited[x][y] = true;
			Map[x][y] += 1;
			Queue.add(cloud);
		}
		
		while (!Queue.isEmpty()) {
			Cloud cloud = Queue.poll();
			int x = cloud.getX();
			int y = cloud.getY();
			int count = 0;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + Cx[i];
				int ny = y + Cy[i];
				
				if (nx < 1 || nx > N || ny < 1 || ny > N) continue;
				
				if(Map[nx][ny] > 0) count++;
			}
			
			Check[x][y] = count;
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				Map[i][j] += Check[i][j];
			}
		}
	}
	
	public static void move(int m) {
		int size = Queue.size();
		int d = D[m];
		int s = S[m];
		
		// Queue에서 구름을 꺼낸다
		for (int i = 0; i < size; i++) {
			Cloud cloud = Queue.poll();
			int x = cloud.getX();
			int y = cloud.getY();
			int count = 0;
			
			while (count < s) {
				int nx = x + Dx[d];
				int ny = y + Dy[d];
				
				if (nx < 1) nx = N;
				if (nx > N) nx = 1;
				if (ny < 1) ny = N;
				if (ny > N) ny = 1;
				
				x = nx;
				y = ny;
				count++;
			}
			
			Queue.add(new Cloud(x, y));	
		}
	}
	
	public static void init() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		Map = new int[N + 1][N + 1];
		D = new int[M + 1];
		S = new int[M + 1];
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				Map[i][j] = sc.nextInt();
			}
		}
		
		for (int i = 1; i <= M; i++) {
			D[i] = sc.nextInt();
			S[i] = sc.nextInt();
		}
		
		Queue.add(new Cloud(N, 1));
		Queue.add(new Cloud(N, 2));
		Queue.add(new Cloud(N - 1, 1));
		Queue.add(new Cloud(N - 1, 2));
		
		sc.close();
	}
}

class Cloud {
	private int x;
	private int y;
	
	public Cloud(int x, int y) {
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