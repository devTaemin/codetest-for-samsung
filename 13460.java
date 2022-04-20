class Marble {
	private int Rx;
	private int Ry;
	private int Bx;
	private int By;
	private int count;
	
	public Marble(int Rx, int Ry, int Bx, int By, int count) {
		this.Rx = Rx;
		this.Ry = Ry;
		this.Bx = Bx;
		this.By = By;
		this.count = count;
	}
	
	public int getRx() {
		return this.Rx;
	}
	
	public int getBx() {
		return this.Bx;
	}
	
	public int getRy() {
		return this.Ry;
	}
	
	public int getBy() {
		return this.By;
	}
	
	public int getCount() {
		return this.count;
	}
	
	public void set(int rdx, int rdy, int bdx, int bdy, int dc) {
		this.Rx += rdx;
		this.Ry += rdy;
		this.Bx += bdx;
		this.By += bdy;
		this.count += dc;
	}
}


class Main {
	public static int N, M;
	public static int HoleX, HoleY, RedX, RedY, BlueX, BlueY;
	public static Marble marble;
	public static char[][] map;
	public static boolean[][][][] visited;
	
	// North, East, South, West
	public static int[] dx = { -1, 0, 1, 0 };
	public static int[] dy = { 0, 1, 0, -1 };
	
	
	public static int DFS() {
		Queue<Marble> queue = new LinkedList<Marble>();
		queue.offer(new Marble(RedX, RedY, BlueX, BlueY, 1));
		visited[RedX][RedY][BlueX][BlueY] = true;
		
		while (!queue.isEmpty()) {
			Marble marble = queue.poll();
			int curRx = marble.getRx();
			int curRy = marble.getRy();
			int curBx = marble.getBx();
			int curBy = marble.getBy();
			int curCount = marble.getCount();
			
			// Check count
			if (curCount > 10) {
				return -1;
			}
			
			// Move
			for (int i = 0; i < 4; i++) {
				int nextRx = curRx;
				int nextRy = curRy;
				int nextBx = curBx;
				int nextBy = curBy;
				
				boolean isRed = false;
				boolean isBlue = false;
				
				// Move Red Marble
				while (map[nextRx + dx[i]][nextRy + dy[i]] != '#') {
					nextRx += dx[i];
					nextRy += dy[i];
					if (map[nextRx][nextRy] == 'O') {
						isRed = true;
						break;
					}
				}
				
				// Move Blue Marble
				while (map[nextBx + dx[i]][nextBy + dy[i]] != '#') {
					nextBx += dx[i];
					nextBy += dy[i];
					if (map[nextBx][nextBy] == 'O') {
						isBlue = true;
						break;
					}
				}
				
				// Check isBlue
				if (isBlue) continue;
				
				// Check isRed
				if (!isBlue && isRed) {
					return curCount;
				}
				
				// Check location
				if (nextRx == nextBx && nextRy == nextBy) {
					// North
					if (i == 0) {
						if (curRx < curBx) { nextBx += 1; } 
						else { nextRx += 1; }
					}
					// East
					if (i == 1) {
						if (curRy < curBy) { nextRy -= 1; } 
						else { nextBy -= 1; }
					}
					// South
					if (i == 2) {
						if (curRx < curBx) { nextRx -= 1; }
						else { nextBx -= 1; }
					}
					// West
					if (i == 3) {
						if (curRy < curBy) { nextBy += 1; }
						else { nextRy += 1; }
					}
				}
				
				if (!visited[nextRx][nextRy][nextBx][nextBy]) {
					queue.offer(new Marble(nextRx, nextRy, nextBx, nextBy, curCount + 1));
				}
				
			}
			
		}
		
		return -1;
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		map = new char[N][M];
		visited = new boolean[N][M][N][M];
		
		for (int i = 0; i < N; i++) {
			String line = sc.next();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
				
				if (map[i][j] == 'O') {
					HoleX = i;
					HoleY = j;
				}
				if (map[i][j] == 'R') {
					RedX = i;
					RedY = j;
				}
				if (map[i][j] == 'B') {
					BlueX = i;
					BlueY = j;
				}
			}
		}
		
		int result = DFS();
		System.out.println(result);
	}	
}