class Virus {
	private int x;
	private int y;
	
	public Virus(int x, int y) {
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
	public static int N, M, Max;
	public static int[][] map;
	public static ArrayList<Virus> container = new ArrayList<Virus>();
	// North, East, South, West
	public static int[] dx = { -1, 0, 1, 0 };
	public static int[] dy = { 0, 1, 0, -1 };
	
	public static void Safe(int[][] copy) {
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copy[i][j] == 0) count++;
			}
		}
		
		Max = Math.max(Max, count);		
	}
	
	public static void BFS() {
		int[][] copy = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copy[i][j] = map[i][j];
			}
		}
		
		Queue<Virus> queue = new LinkedList<Virus>();
		Iterator<Virus> iter = container.iterator();
		while(iter.hasNext()) {
			queue.add(iter.next());
		}
		
		while (!queue.isEmpty()) {
			Virus virus = queue.poll();
			
			// North, East, South, West
			for (int i = 0; i < 4; i++) {
				int nx = virus.getX() + dx[i];
				int ny = virus.getY() + dy[i];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				
				if (copy[nx][ny] == 0) {
					copy[nx][ny] = 2;
					queue.add(new Virus(nx, ny));
				}
			}
		}
		
		Safe(copy);
	}
	
	public static void DFS(int target, int depth) {
		if (depth == target) {
			BFS();			
			return;
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					map[i][j] = 1;
					DFS(target, depth + 1);
					map[i][j] = 0;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 2) {
					container.add(new Virus(i, j));
				}
			}
		}
		
		DFS(3, 0);
		System.out.println(Max);
		sc.close();
	}
}