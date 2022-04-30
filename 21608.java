class Desk {
	int x;
	int y;
	int count;
	
	public Desk(int x, int y, int count) {
		this.x = x;
		this.y = y;
		this.count = count;
	}
}

class Main {
	public static int N, M;
	public static int Result;
	public static int Max;
	public static int[][] Map;
	public static int[] Student;
	public static int[][] Favorite;
	public static Queue<Desk> Q;
	public static Queue<Desk> E;
	
	// North, East, South, West
	public static int[] Dx = { -1, 0, 1, 0 };
	public static int[] Dy = { 0, 1, 0, -1 };
	
	public static void main(String[] args) {
		init();
		run();
		count();
	}
	
	public static void count() {
		for (int x = 1; x <= N; x++) {
			for (int y = 1; y <= N; y++) {
				int numOfStudent = Map[x][y];
				double count = 0;
				
				for (int d = 0; d < 4; d++) {
					int nx = x + Dx[d];
					int ny = y + Dy[d];
					
					if (nx < 1 || nx > N || ny < 1 || ny > N) continue;
					
					for (int s = 0; s < 4; s++) {
						if (Map[nx][ny] == Favorite[numOfStudent][s]) {
							count++;
							break;
						}
					}
				}
				// 0 1 10 100 1000
				if (count >= 1) Result += Math.pow(10, count - 1);
			}
		}
		System.out.println(Result);
	}
	
	public static void checkSecond() {
		E = new LinkedList<Desk>();
		
		Max = 0;
		while(!Q.isEmpty()) {
			Desk desk = Q.poll();
			int count = 0;
			
			// North, East, South, West
			for (int d = 0; d < 4; d++) {
				int nx = desk.x + Dx[d];
				int ny = desk.y + Dy[d];
				
				if (nx < 1 || nx > N || ny < 1 || ny > N) continue;
				
				if (Map[nx][ny] == 0) count++;
			}
			
			if (Max < count) {
				E = new LinkedList<Desk>();
				E.add(new Desk(desk.x, desk.y, count));
				Max = count;
			}
			
			if (Max == count) {
				E.add(new Desk(desk.x, desk.y, count));
			}
			
		}
	}
	
	public static void check(int i) {
		int numOfStudent = Student[i];
		Q = new LinkedList<Desk>();
		Max = 0;
		
		for (int x = 1; x <= N; x++) {
			for (int y = 1; y <= N; y++) {
				
				if (Map[x][y] != 0) continue;
				
				int count = 0;
				
				// North, East, South, West
				for (int d = 0; d < 4; d++) {
					int nx = x + Dx[d];
					int ny = y + Dy[d];
					
					if (nx < 1 || nx > N || ny < 1 || ny > N) continue;
					
					for (int s = 0; s < 4; s++) {
						if (Map[nx][ny] == Favorite[numOfStudent][s]) {
							count++;
							break;
						}
					}
				}
				
				if (Max < count) {
					Q = new LinkedList<Desk>();
					Q.add(new Desk(x, y, count));
					Max = count;
				}
				
				if (Max == count) {
					Q.add(new Desk(x, y, count));
				}
			}
		}
	}
	
	public static void run() {
		
		for (int i = 1; i <= M; i++) {
			
			check(i);
			
			if (Q.size() == 1) {
				Desk desk = Q.poll();
				Map[desk.x][desk.y] = Student[i];
				continue;
			}
			
			checkSecond();
			
			Desk desk = E.poll();
			Map[desk.x][desk.y] = Student[i];
		}
		
	}
	
	public static void init() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = N * N;
		
		Map = new int[N + 1][N + 1];
		Student = new int[M + 1];
		Favorite = new int[M + 1][4];
		Result = 0;

		
		for (int i = 1; i <= M; i++) {
			int numOfStudent = sc.nextInt();
			Student[i] = numOfStudent;
			
			for (int j = 0; j < 4; j++) {
				Favorite[numOfStudent][j] = sc.nextInt();
			}
		}
		
		sc.close();
	}
}