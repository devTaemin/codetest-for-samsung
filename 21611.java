class Main {
	public static int N, M;
	public static int X, Y;
	public static int First, Second, Third;
	public static int[][] Map;
	public static int[][] Copy;
	public static int[] D, S;
	public static Queue<Marble> Queue;
	public static Queue<Marble> Explode;
	public static Queue<Integer> Result;
	
	// North, South, West, East
	public static int[] Dx = { 0, -1, 1, 0, 0 };
	public static int[] Dy = { 0, 0, 0, -1, 1 };
	
	public static void main(String[] args) {
		init();
		run();
	}
	
	public static void run() {
		
		for (int magic = 1; magic <= M; magic++) {
			X = (N + 1) / 2;
			Y = (N + 1) / 2;
			
			attack(magic);			
			move();
			Map = Copy;
			
			while(find()) {
				explode();
				move();
				Map = Copy;
			}
			
			change();
			paste();
			Map = Copy;
		}
		
		result();
	}
	
	public static void result() {
		System.out.println(First + Second * 2 + Third * 3);
	}
	
	public static void paste() {
		Copy = new int[N + 1][N + 1];
		X = (N + 1) / 2;
		Y = (N + 1) / 2;
		int left = 0;
		int down = 0;
		int right = 0;
		int up = 0;
		int size = Result.size();
		int count = 0;
		
		while (true) {
			left = right + 1;
			for (int i = 1; i <= left; i++) {
				int nx = X + Dx[3];
				int ny = Y + Dy[3];
				
				if (!isRange(nx, ny)) return;
				
				int number = Result.poll();
				count++;
				
				Copy[nx][ny] = number;
				if (size <= count) return;
				
				X = nx;
				Y = ny;
			}
			
			down = up + 1;
			for (int i = 1; i <= down; i++) {
				int nx = X + Dx[2];
				int ny = Y + Dy[2];
				
				if (!isRange(nx, ny)) return;
				
				int number = Result.poll();
				count++;
				
				Copy[nx][ny] = number;
				if (size <= count) return;
				
				X = nx;
				Y = ny;
			}
			
			right = left + 1;
			for (int i = 1; i <= right; i++) {
				int nx = X + Dx[4];
				int ny = Y + Dy[4];
				
				if (!isRange(nx, ny)) return;
				
				int number = Result.poll();
				count++;
				
				Copy[nx][ny] = number;
				if (size <= count) return;
				
				X = nx;
				Y = ny;
			}
			
			up = down + 1;
			for (int i = 1; i <= up; i++) {
				int nx = X + Dx[1];
				int ny = Y + Dy[1];
				
				if (!isRange(nx, ny)) return;
				
				int number = Result.poll();
				count++;
				
				Copy[nx][ny] = number;
				if (size <= count) return;
				
				X = nx;
				Y = ny;
			}
			
			
		}
	}
	
	public static void change() {
		Queue = new LinkedList<Marble>();
		Result = new LinkedList<Integer>();
		X = (N + 1) / 2;
		Y = (N + 1) / 2;
		int left = 0;
		int down = 0;
		int right = 0;
		int up = 0;
		int current = 0;
		
		while (true) {
			left = right + 1;
			for (int i = 1; i <= left; i++) {
				int nx = X + Dx[3];
				int ny = Y + Dy[3];
				
				if (!isRange(nx, ny)) {
					Result.add(Queue.size());
					Result.add(current);
					break;
				}
				
				if (Map[nx][ny] != current) {
					if (Map[nx][ny] == 0) {
						Result.add(Queue.size());
						Result.add(current);
						return;
					}
					else {
						if (current != 0) {
							Result.add(Queue.size());
							Result.add(current);
						}
						
						Queue = new LinkedList<Marble>();
						current = Map[nx][ny];
						Queue.add(new Marble(nx, ny, current));
					}
				}
				else {
					Queue.add(new Marble(nx, ny, current));
				}
				
				X = nx;
				Y = ny;
			}
			
			if (X == 1 && Y == 1) break;
			
			down = up + 1;
			for (int i = 1; i <= down; i++) {
				int nx = X + Dx[2];
				int ny = Y + Dy[2];
				
				if (!isRange(nx, ny)) {
					Result.add(Queue.size());
					Result.add(current);
					break;
				}
				
				if (Map[nx][ny] != current) {
					if (Map[nx][ny] == 0) {
						Result.add(Queue.size());
						Result.add(current);
						return;
					}
					else {
						if (current != 0) {
							Result.add(Queue.size());
							Result.add(current);
						}
						
						Queue = new LinkedList<Marble>();
						current = Map[nx][ny];
						Queue.add(new Marble(nx, ny, current));
					}
				}
				else {
					Queue.add(new Marble(nx, ny, current));
				}
				
				X = nx;
				Y = ny;
			}
			
			right = left + 1;
			for (int i = 1; i <= right; i++) {
				int nx = X + Dx[4];
				int ny = Y + Dy[4];
				
				if (!isRange(nx, ny)) {
					Result.add(Queue.size());
					Result.add(current);
					break;
				}
				
				if (Map[nx][ny] != current) {
					if (Map[nx][ny] == 0) {
						Result.add(Queue.size());
						Result.add(current);
						return;
					}
					else {
						if (current != 0) {
							Result.add(Queue.size());
							Result.add(current);
						}
						
						Queue = new LinkedList<Marble>();
						current = Map[nx][ny];
						Queue.add(new Marble(nx, ny, current));
					}
				}
				else {
					Queue.add(new Marble(nx, ny, current));
				}
				
				X = nx;
				Y = ny;
			}
			
			up = down + 1;
			for (int i = 1; i <= up; i++) {
				int nx = X + Dx[1];
				int ny = Y + Dy[1];
				
				if (!isRange(nx, ny)) {
					Result.add(Queue.size());
					Result.add(current);
					break;
				}
				
				if (Map[nx][ny] != current) {
					if (Map[nx][ny] == 0) {
						Result.add(Queue.size());
						Result.add(current);
						return;
					}
					else {
						if (current != 0) {
							Result.add(Queue.size());
							Result.add(current);
						}
						
						Queue = new LinkedList<Marble>();
						current = Map[nx][ny];
						Queue.add(new Marble(nx, ny, current));
					}
				}
				else {
					Queue.add(new Marble(nx, ny, current));
				}
				
				X = nx;
				Y = ny;
			}
		}
		
	}
	
	public static void explode() {
		
		while (!Explode.isEmpty()) {
			Marble marble = Explode.poll();
			int x = marble.getX();
			int y = marble.getY();
			int number = marble.getNumber();
			
			Map[x][y] = 0;
			if (number == 1) First += 1;
			if (number == 2) Second += 1;
			if (number == 3) Third += 1;
		}
	}
	
	public static boolean find() {
		Queue = new LinkedList<Marble>();
		Explode = new LinkedList<Marble>();
		X = (N + 1) / 2;
		Y = (N + 1) / 2;
		int left = 0;
		int down = 0;
		int right = 0;
		int up = 0;
		int current = 0;		// 1, 2, 3
		
		boolean find = false;
		
		while (true) {
			left = right + 1;
			for (int i = 1; i <= left; i++) {
				int nx = X + Dx[3];
				int ny = Y + Dy[3];
				
				if (!isRange(nx, ny)) break;
				
				if (current != Map[nx][ny] || Map[nx][ny] == 0) {
					if (Queue.size() >= 4) {
						find = true;
						while (!Queue.isEmpty()) {
							Explode.add(Queue.poll());
						}
						
						Queue = new LinkedList<Marble>();
						current = Map[nx][ny];
					}
					else {
						Queue = new LinkedList<Marble>();
						Queue.add(new Marble(nx, ny, Map[nx][ny]));
						current = Map[nx][ny];
					}
				}
				else {
					Queue.add(new Marble(nx, ny, Map[nx][ny]));
				}
				
				X = nx;
				Y = ny;
			}
			
			if (X == 1 && Y == 1) break;
			
			
			down = up + 1;
			for (int i = 1; i <= down; i++) {
				int nx = X + Dx[2];
				int ny = Y + Dy[2];
				
				if (!isRange(nx, ny)) break;
				
				if (current != Map[nx][ny] || Map[nx][ny] == 0) {
					if (Queue.size() >= 4) {
						find = true;
						while (!Queue.isEmpty()) {
							Explode.add(Queue.poll());
						}
						
						Queue = new LinkedList<Marble>();
						current = Map[nx][ny];
					}
					else {
						Queue = new LinkedList<Marble>();
						Queue.add(new Marble(nx, ny, Map[nx][ny]));
						current = Map[nx][ny];
					}
				}
				else {
					Queue.add(new Marble(nx, ny, Map[nx][ny]));
				}
				
				X = nx;
				Y = ny;
			}
			
			right = left + 1;
			for (int i = 1; i <= right; i++) {
				int nx = X + Dx[4];
				int ny = Y + Dy[4];
				
				if (!isRange(nx, ny)) break;
				
				if (current != Map[nx][ny] || Map[nx][ny] == 0) {
					if (Queue.size() >= 4) {
						find = true;
						while (!Queue.isEmpty()) {
							Explode.add(Queue.poll());
						}
						
						Queue = new LinkedList<Marble>();
						current = Map[nx][ny];
					}
					else {
						Queue = new LinkedList<Marble>();
						Queue.add(new Marble(nx, ny, Map[nx][ny]));
						current = Map[nx][ny];
					}
				
				}
				else {
					Queue.add(new Marble(nx, ny, Map[nx][ny]));
				}
				
				X = nx;
				Y = ny;
			}
			
			up = down + 1;
			for (int i = 1; i <= up; i++) {
				int nx = X + Dx[1];
				int ny = Y + Dy[1];
				
				if (!isRange(nx, ny)) break;
				
				if (current != Map[nx][ny] || Map[nx][ny] == 0) {
					if (Queue.size() >= 4) {
						find = true;
						while (!Queue.isEmpty()) {
							Explode.add(Queue.poll());
						}
						
						Queue = new LinkedList<Marble>();
						current = Map[nx][ny];
					}
					else {
						Queue = new LinkedList<Marble>();
						Queue.add(new Marble(nx, ny, Map[nx][ny]));
						current = Map[nx][ny];
					}
				}
				else {
					Queue.add(new Marble(nx, ny, Map[nx][ny]));
				}
				
				X = nx;
				Y = ny;
			}
		}
		
		return find;
	}
	
	public static void move() {
		Queue = new LinkedList<Marble>();
		Copy = new int[N + 1][N + 1];
		X = (N + 1) / 2;
		Y = (N + 1) / 2;
		int left = 0;
		int down = 0;
		int right = 0;
		int up = 0;
	
		while (true) {
			left = right + 1;
			checkMarble(left, 3);

			if (X == 1 && Y == 1) break;
			
			down = up + 1;
			checkMarble(down, 2);
			
			right = left + 1;
			checkMarble(right, 4);
			
			up = down + 1;
			checkMarble(up, 1);
		}
		
		X = (N + 1) / 2;
		Y = (N + 1) / 2;
		left = 0;
		down = 0;
		right = 0;
		up = 0;
		int size = Queue.size();
		int count = 0;
		
		if (size == 0) return; 
		
		while (true) {
			left = right + 1;
			for (int i = 1; i <= left; i++) {
				Marble marble = Queue.poll();
				count++;
				
				int nx = X + Dx[3];
				int ny = Y + Dy[3];
				
				if (!isRange(nx, ny)) return;
				X = nx;
				Y = ny;
				
				Copy[X][Y] = marble.getNumber();
				if (count >= size) return;
			}
			
			if (X == 1 && Y == 1) return;
			
			down = up + 1;
			for (int i = 1; i <= down; i++) {
				Marble marble = Queue.poll();
				count++;
				
				int nx = X + Dx[2];
				int ny = Y + Dy[2];
				
				if (!isRange(nx, ny)) return;
				X = nx;
				Y = ny;
				Copy[X][Y] = marble.getNumber();
				if (count >= size) return;
			}
			
			right = left + 1;
			for (int i = 1; i <= right; i++) {
				Marble marble = Queue.poll();
				count++;
				
				int nx = X + Dx[4];
				int ny = Y + Dy[4];
				
				if (!isRange(nx, ny)) return;
				X = nx;
				Y = ny;
				Copy[X][Y] = marble.getNumber();
				if (count >= size) return;
			}
			
			up = down + 1;
			for (int i = 1; i <= up; i++) {
				Marble marble = Queue.poll();
				count++;
				
				int nx = X + Dx[1];
				int ny = Y + Dy[1];
				
				if (!isRange(nx, ny)) return;
				X = nx;
				Y = ny;
				Copy[X][Y] = marble.getNumber();
				if (count >= size) return;
			}
		}
	}
	
	
	
	public static void checkMarble(int count, int direction) {
		for (int i = 1; i <= count; i++) {
			int nx = X + Dx[direction];
			int ny = Y + Dy[direction];
			
			if (!isRange(nx, ny)) return;
			
			if (Map[nx][ny] > 0) {
				Queue.add(new Marble(nx, ny, Map[nx][ny]));
			}
			
			X = nx;
			Y = ny;
		}
	}
	
	public static void attack(int magic) {
		int x = X;
		int y = Y;
		int count = 0;
		int range = S[magic];
		
		while (count < range) {
			int nx = x + Dx[D[magic]];
			int ny = y + Dy[D[magic]];
			
			if (!isRange(nx,ny)) break;
			
			Map[nx][ny] = 0;
			x = nx;
			y = ny;
			count++;
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
		
		First = 0;
		Second = 0;
		Third = 0;
		X = (N + 1) / 2;
		Y = (N + 1) / 2;
		
		sc.close();
	}
	
	public static boolean isRange(int x, int y) {
		if (x < 1 || x > N || y < 1 || y > N) return false;
		else return true;
	}
	
	public static void print() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				System.out.print(Map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}

class Marble {
	private int x;
	private int y;
	private int number;
	
	public Marble(int x, int y, int number) {
		this.x = x;
		this.y = y;
		this.number = number;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getNumber() {
		return this.number;
	}
}