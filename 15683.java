class CCTV {
	private int x;
	private int y;
	private int type;
	private int direction;
	
	public CCTV(int x, int y, int type, int direction) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.direction = direction;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getType() {
		return this.type;
	}
	
	public int getDirection() {
		return this.direction;
	}
	
	public void setDirection(int d) {
		this.direction = d;
	}
}

class Main {
	public static int N, M;
	public static int Result = (int)1e9;
	public static int[][] Map;
	public static ArrayList<CCTV> Array = new ArrayList<CCTV>();
	public static int[] Rotation;
	public static int[] Dx = { 0, 1, 0, -1 };
	public static int[] Dy = { 1, 0, -1, 0 };
	
	
	// Refactoring is Needed!!!
	public static void Check() {
		int[][] copy = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copy[i][j] = Map[i][j];
			}
		}
		
		Iterator<CCTV> iter = Array.iterator();
		while(iter.hasNext()) {
			CCTV temp = iter.next();
			int x = temp.getX();
			int y = temp.getY();
			int t = temp.getType();
			int d = temp.getDirection();
			
			copy[x][y] = -1;
			
			if (t == 1) {
				while(true) {
					int nx = x + Dx[d];
					int ny = y + Dy[d];
					
					if (nx < 0 || nx >= N || ny < 0 || ny >= M) break;
					if (copy[nx][ny] == 6) break;
					
					copy[nx][ny] = -1;
					x = nx;
					y = ny;
				}
			}
			
			if (t == 2) {
				if (d == 0 || d == 2) {
					int oX = x;
					int oY = y;
					while(true) {
						int nx = oX + Dx[0];
						int ny = oY + Dy[0];
						
						if (nx < 0 || nx >= N || ny < 0 || ny >= M) break;
						if (copy[nx][ny] == 6) break;
						
						copy[nx][ny] = -1;
						oX = nx;
						oY = ny;
					}
					
					int ooX = x;
					int ooY = y;
					while(true) {
						int nx = ooX + Dx[2];
						int ny = ooY + Dy[2];
						
						if (nx < 0 || nx >= N || ny < 0 || ny >= M) break;
						if (copy[nx][ny] == 6) break;
						
						copy[nx][ny] = -1;
						ooX = nx;
						ooY = ny;
					}
				}
				
				if (d == 1 || d == 3) {
					int oX = x;
					int oY = y;
					while(true) {
						int nx = oX + Dx[1];
						int ny = oY + Dy[1];
						
						if (nx < 0 || nx >= N || ny < 0 || ny >= M) break;
						if (copy[nx][ny] == 6) break;
						
						copy[nx][ny] = -1;
						oX = nx;
						oY = ny;
					}
					
					int ooX = x;
					int ooY = y;
					while(true) {
						int nx = ooX + Dx[3];
						int ny = ooY + Dy[3];
						
						if (nx < 0 || nx >= N || ny < 0 || ny >= M) break;
						if (copy[nx][ny] == 6) break;
						
						copy[nx][ny] = -1;
						ooX = nx;
						ooY = ny;
					}
				}
			}
			
			if (t == 3) {
				if (d == 0) {
					int oX = x;
					int oY = y;
					while(true) {
						int nx = oX + Dx[0];
						int ny = oY + Dy[0];
						
						if (nx < 0 || nx >= N || ny < 0 || ny >= M) break;
						if (copy[nx][ny] == 6) break;
						
						copy[nx][ny] = -1;
						oX = nx;
						oY = ny;
					}
					
					int ooX = x;
					int ooY = y;
					while(true) {
						int nx = ooX + Dx[3];
						int ny = ooY + Dy[3];
						
						if (nx < 0 || nx >= N || ny < 0 || ny >= M) break;
						if (copy[nx][ny] == 6) break;
						
						copy[nx][ny] = -1;
						ooX = nx;
						ooY = ny;
					}
				}
				
				if (d == 1) {
					int oX = x;
					int oY = y;
					while(true) {
						int nx = oX + Dx[0];
						int ny = oY + Dy[0];
						
						if (nx < 0 || nx >= N || ny < 0 || ny >= M) break;
						if (copy[nx][ny] == 6) break;
						
						copy[nx][ny] = -1;
						oX = nx;
						oY = ny;
					}
					
					int ooX = x;
					int ooY = y;
					while(true) {
						int nx = ooX + Dx[1];
						int ny = ooY + Dy[1];
						
						if (nx < 0 || nx >= N || ny < 0 || ny >= M) break;
						if (copy[nx][ny] == 6) break;
						
						copy[nx][ny] = -1;
						ooX = nx;
						ooY = ny;
					}
				}
				
				if (d == 2) {
					int oX = x;
					int oY = y;
					while(true) {
						int nx = oX + Dx[1];
						int ny = oY + Dy[1];
						
						if (nx < 0 || nx >= N || ny < 0 || ny >= M) break;
						if (copy[nx][ny] == 6) break;
						
						copy[nx][ny] = -1;
						oX = nx;
						oY = ny;
					}
					
					int ooX = x;
					int ooY = y;
					while(true) {
						int nx = ooX + Dx[2];
						int ny = ooY + Dy[2];
						
						if (nx < 0 || nx >= N || ny < 0 || ny >= M) break;
						if (copy[nx][ny] == 6) break;
						
						copy[nx][ny] = -1;
						ooX = nx;
						ooY = ny;
					}
				}
				
				if (d == 3) {
					int oX = x;
					int oY = y;
					while(true) {
						int nx = oX + Dx[2];
						int ny = oY + Dy[2];
						
						if (nx < 0 || nx >= N || ny < 0 || ny >= M) break;
						if (copy[nx][ny] == 6) break;
						
						copy[nx][ny] = -1;
						oX = nx;
						oY = ny;
					}
					
					int ooX = x;
					int ooY = y;
					while(true) {
						int nx = ooX + Dx[3];
						int ny = ooY + Dy[3];
						
						if (nx < 0 || nx >= N || ny < 0 || ny >= M) break;
						if (copy[nx][ny] == 6) break;
						
						copy[nx][ny] = -1;
						ooX = nx;
						ooY = ny;
					}
				}
			}
			
			if (t == 4) {
				if (d == 0) {
					int oX = x;
					int oY = y;
					while(true) {
						int nx = oX + Dx[0];
						int ny = oY + Dy[0];
						
						if (nx < 0 || nx >= N || ny < 0 || ny >= M) break;
						if (copy[nx][ny] == 6) break;
						
						copy[nx][ny] = -1;
						oX = nx;
						oY = ny;
					}
					
					int ooX = x;
					int ooY = y;
					while(true) {
						int nx = ooX + Dx[2];
						int ny = ooY + Dy[2];
						
						if (nx < 0 || nx >= N || ny < 0 || ny >= M) break;
						if (copy[nx][ny] == 6) break;
						
						copy[nx][ny] = -1;
						ooX = nx;
						ooY = ny;
					}
					
					int oooX = x;
					int oooY = y;
					while(true) {
						int nx = oooX + Dx[3];
						int ny = oooY + Dy[3];
						
						if (nx < 0 || nx >= N || ny < 0 || ny >= M) break;
						if (copy[nx][ny] == 6) break;
						
						copy[nx][ny] = -1;
						oooX = nx;
						oooY = ny;
					}
				}
				
				if (d == 1) {
					int oX = x;
					int oY = y;
					while(true) {
						int nx = oX + Dx[0];
						int ny = oY + Dy[0];
						
						if (nx < 0 || nx >= N || ny < 0 || ny >= M) break;
						if (copy[nx][ny] == 6) break;
						
						copy[nx][ny] = -1;
						oX = nx;
						oY = ny;
					}
					
					int ooX = x;
					int ooY = y;
					while(true) {
						int nx = ooX + Dx[1];
						int ny = ooY + Dy[1];
						
						if (nx < 0 || nx >= N || ny < 0 || ny >= M) break;
						if (copy[nx][ny] == 6) break;
						
						copy[nx][ny] = -1;
						ooX = nx;
						ooY = ny;
					}
					
					int oooX = x;
					int oooY = y;
					while(true) {
						int nx = oooX + Dx[3];
						int ny = oooY + Dy[3];
						
						if (nx < 0 || nx >= N || ny < 0 || ny >= M) break;
						if (copy[nx][ny] == 6) break;
						
						copy[nx][ny] = -1;
						oooX = nx;
						oooY = ny;
					}
				}
				
				if (d == 2) {
					int oX = x;
					int oY = y;
					while(true) {
						int nx = oX + Dx[0];
						int ny = oY + Dy[0];
						
						if (nx < 0 || nx >= N || ny < 0 || ny >= M) break;
						if (copy[nx][ny] == 6) break;
						
						copy[nx][ny] = -1;
						oX = nx;
						oY = ny;
					}
					
					int ooX = x;
					int ooY = y;
					while(true) {
						int nx = ooX + Dx[1];
						int ny = ooY + Dy[1];
						
						if (nx < 0 || nx >= N || ny < 0 || ny >= M) break;
						if (copy[nx][ny] == 6) break;
						
						copy[nx][ny] = -1;
						ooX = nx;
						ooY = ny;
					}
					
					int oooX = x;
					int oooY = y;
					while(true) {
						int nx = oooX + Dx[2];
						int ny = oooY + Dy[2];
						
						if (nx < 0 || nx >= N || ny < 0 || ny >= M) break;
						if (copy[nx][ny] == 6) break;
						
						copy[nx][ny] = -1;
						oooX = nx;
						oooY = ny;
					}
				}
				
				if (d == 3) {
					int oX = x;
					int oY = y;
					while(true) {
						int nx = oX + Dx[1];
						int ny = oY + Dy[1];
						
						if (nx < 0 || nx >= N || ny < 0 || ny >= M) break;
						if (copy[nx][ny] == 6) break;
						
						copy[nx][ny] = -1;
						oX = nx;
						oY = ny;
					}
					
					int ooX = x;
					int ooY = y;
					while(true) {
						int nx = ooX + Dx[2];
						int ny = ooY + Dy[2];
						
						if (nx < 0 || nx >= N || ny < 0 || ny >= M) break;
						if (copy[nx][ny] == 6) break;
						
						copy[nx][ny] = -1;
						ooX = nx;
						ooY = ny;
					}
					
					int oooX = x;
					int oooY = y;
					while(true) {
						int nx = oooX + Dx[3];
						int ny = oooY + Dy[3];
						
						if (nx < 0 || nx >= N || ny < 0 || ny >= M) break;
						if (copy[nx][ny] == 6) break;
						
						copy[nx][ny] = -1;
						oooX = nx;
						oooY = ny;
					}
				}
			}
			
			if (t == 5) {
				int oX = x;
				int oY = y;
				while(true) {
					int nx = oX + Dx[0];
					int ny = oY + Dy[0];
					
					if (nx < 0 || nx >= N || ny < 0 || ny >= M) break;
					if (copy[nx][ny] == 6) break;
					
					copy[nx][ny] = -1;
					oX = nx;
					oY = ny;
				}
				
				int ooX = x;
				int ooY = y;
				while(true) {
					int nx = ooX + Dx[1];
					int ny = ooY + Dy[1];
					
					if (nx < 0 || nx >= N || ny < 0 || ny >= M) break;
					if (copy[nx][ny] == 6) break;
					
					copy[nx][ny] = -1;
					ooX = nx;
					ooY = ny;
				}
				
				int oooX = x;
				int oooY = y;
				while(true) {
					int nx = oooX + Dx[2];
					int ny = oooY + Dy[2];
					
					if (nx < 0 || nx >= N || ny < 0 || ny >= M) break;
					if (copy[nx][ny] == 6) break;
					
					copy[nx][ny] = -1;
					oooX = nx;
					oooY = ny;
				}
				
				int ooooX = x;
				int ooooY = y;
				while(true) {
					int nx = ooooX + Dx[3];
					int ny = ooooY + Dy[3];
					
					if (nx < 0 || nx >= N || ny < 0 || ny >= M) break;
					if (copy[nx][ny] == 6) break;
					
					copy[nx][ny] = -1;
					ooooX = nx;
					ooooY = ny;
				}
			}
		}
		
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copy[i][j] == 0) count++;
			}
		}
		
		Result = (Result < count) ? Result : count;
	}
	
	public static void DFS(int size, int depth) {
		if (depth == size) {			
			Iterator<CCTV> iter = Array.iterator();
			int index = 0;
			
			while(iter.hasNext()) {
				CCTV temp = iter.next();
				temp.setDirection(Rotation[index++]);
			}
			
			Check();
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			Rotation[depth] = i;
			DFS(size, depth + 1);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		Map = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				Map[i][j] = sc.nextInt();
				if (Map[i][j] == 0) continue;
				else if (Map[i][j] == 6) continue;
				else {
					Array.add(new CCTV(i, j, Map[i][j], 0));
				}
			}
		}
		
		
		int size = Array.size();
		Rotation = new int[size];
		DFS(size, 0);
		System.out.println(Result);
	}
}