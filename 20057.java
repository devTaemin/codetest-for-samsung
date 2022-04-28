class Main {
	public static int N;
	public static int X, Y;
	public static int Result;
	public static int[][] Map;
	
	// Left, Down, Right, Up
	public static int[] Dx = { 0, 1, 0, -1 };
	public static int[] Dy = { -1, 0, 1, 0 };
	
	public static int[] LeftX = { 0, -1, 1, -2, -1, 1, 2, -1, 1 };
	public static int[] LeftY = { -2, -1, -1, 0, 0, 0, 0, 1, 1 };
	public static int[] LeftP = { 5, 10, 10, 2, 7, 7, 2, 1, 1 };
	
	public static int[] DownX = { -1, -1, 0, 0, 0, 0, 1, 1, 2 };
	public static int[] DownY = { -1, 1, -2, -1, 1, 2, -1, 1, 0 };
	public static int[] DownP = { 1, 1, 2, 7, 7, 2, 10, 10, 5};
	
	public static int[] RightX = { -1, 1, -2, -1, 1, 2, -1, 1, 0 };
	public static int[] RightY = { -1, -1, 0, 0, 0, 0, 1, 1, 2 };
	public static int[] RightP = { 1, 1, 2, 7, 7, 2, 10, 10, 5 }; 
	
	public static int[] UpX = { 1, 1, 0, 0, 0, 0, -1, -1, -2 };
	public static int[] UpY = { -1, 1, -2, -1, 1, 2, -1, 1, 0 };
	public static int[] UpP = { 1, 1, 2, 7, 7, 2, 10, 10, 5 };
	
	public static void main(String[] args) {
		init();
		tornado();
		check();
	}
	
	public static void check() {
		Result = 0;
		for (int i = 0; i < N + 4; i++) {
			for (int j = 0; j < N + 4; j++) {
				
				if (!( (i >= 2 && i < N + 2) && (j >= 2 && j < N + 2)) ) {
					Result += Map[i][j];
				}
			}
		}
		
		System.out.println(Result);
	}
	
	public static void moveLeft(int count) {
		for (int i = 0; i < count; i++) {
			int nx = X + Dx[0];
			int ny = Y + Dy[0];

			int sand = Map[nx][ny];
			int movedTotal = 0;
			
			for (int j = 0; j < 9; j++) {
				int movedSand = (sand * LeftP[j] / 100);
				Map[nx + LeftX[j]][ny + LeftY[j]] += movedSand;
				movedTotal += movedSand;
			}
			
			if (sand > 0) {
				Map[nx][ny - 1] += sand - movedTotal;
				Map[nx][ny] = 0;
			}
			
			
			X = nx;
			Y = ny;
			
			if (X == 2 && Y == 2) return;
		}
	}
	
	public static void moveDown(int count) {
		for (int i = 0; i < count; i++) {
			int nx = X + Dx[1];
			int ny = Y + Dy[1];
			
			int sand = Map[nx][ny];
			int movedTotal = 0;
			
			for (int j = 0; j < 9; j++) {
				int movedSand = (sand * DownP[j] / 100);
				Map[nx + DownX[j]][ny + DownY[j]] += movedSand;
				movedTotal += movedSand;
			}
			
			if (sand > 0) {
				Map[nx + 1][ny] += sand - movedTotal;
				Map[nx][ny] = 0;
			}
			
			X = nx;
			Y = ny;
		}
	}
	
	public static void moveRight(int count) {
		for (int i = 0; i < count; i++) {
			int nx = X + Dx[2];
			int ny = Y + Dy[2];
			
			int sand = Map[nx][ny];
			int movedTotal = 0;
			
			for (int j = 0; j < 9; j++) {
				int movedSand = (sand * RightP[j] / 100);
				Map[nx + RightX[j]][ny + RightY[j]] += movedSand;
				movedTotal += movedSand;
			}
			
			if (sand > 0) {
				Map[nx][ny + 1] += sand - movedTotal;
				Map[nx][ny] = 0;
			}
			
			X = nx;
			Y = ny;
		}
	}
	
	public static void moveUp(int count) {
		for (int i = 0; i < count; i++) {
			int nx = X + Dx[3];
			int ny = Y + Dy[3];
			
			int sand = Map[nx][ny];
			int movedTotal = 0;
			
			for (int j = 0; j < 9; j++) {
				int movedSand = (sand * UpP[j] / 100);
				Map[nx + UpX[j]][ny + UpY[j]] += movedSand;
				movedTotal += movedSand;
			}
			
			if (sand > 0) {
				Map[nx - 1][ny] += sand - movedTotal;
				Map[nx][ny] = 0;
			}
			
			
			X = nx;
			Y = ny;
		}
	}
	
	public static void tornado() {
		int left = 0;
		int down = 0;
		int right = 0;
		int up = 0;
		
		while (true) {
			left = right + 1;
			moveLeft(left);
			
			if (isArrived()) break;
			
			down = up + 1;
			moveDown(down);
			
			right = left + 1;
			moveRight(right);
			
			up = down + 1;
			moveUp(up);
		}
	}
	
	public static boolean isArrived() {
		if (X == 2 && Y == 2) return true;
		else return false;
	}
	
	public static void init() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		Map = new int[N + 4][N + 4];
		
		for (int i = 2; i < N + 2; i++) {
			for (int j = 2; j < N + 2; j++) {
				Map[i][j] = sc.nextInt();
			}
		}
		
		X = (N + 3) / 2;
		Y = (N + 3) / 2;
		
		sc.close();
	}
}