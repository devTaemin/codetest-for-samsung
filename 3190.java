import java.util.*;
import java.io.*;

class Tail {
	private int x;
	private int y;
	
	public Tail(int x, int y) {
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
	public static int count;
	public static int N, K, L;
	public static int[][] map;
	public static char[] timeTable;
	// North(0), East(1), South(2), West(3)
	public static int[] dx = { -1, 0, 1, 0 };
	public static int[] dy = { 0, 1, 0, -1 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1]; // 1 ~ N
		timeTable = new char[10001]; // 0 ~ 10000
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map[x][y] = 1; // Apple
		}
		
		L = Integer.parseInt(br.readLine());
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			char direction = st.nextToken().charAt(0);
			timeTable[time] = direction;
		}
		
		Dummy();
		bw.write(count + "\n");
		bw.flush();
		bw.close();
	}
	
	public static void Dummy() {
		Queue<Tail> snake = new LinkedList<Tail>();
		int[] headIndex = { 1, 1 };
		snake.offer(new Tail(headIndex[0], headIndex[1]));
		map[headIndex[0]][headIndex[1]] = -1;
		int d = 1;
		count = 0;
		
		while (true) {
			
			if(timeTable[count] == 'D') {
				d = (d + 1) % 4;
			}
			
			if(timeTable[count] == 'L') {
				if (d == 0) d = 3;
				else d -= 1;
			}
			
			int nx = headIndex[0] + dx[d];
			int ny = headIndex[1] + dy[d];
			count += 1;
			
			// Check range
			if (nx < 1 || nx > N || ny < 1 || ny > N) return;
			
			// Check tail
			if (map[nx][ny] == -1) return;

			// Check Apple
			if (map[nx][ny] == 1) {
				snake.offer(new Tail(nx, ny));
				map[nx][ny] = -1;
				headIndex[0] = nx;
				headIndex[1] = ny;
			}
			else {
				Tail tail = snake.poll();
				map[tail.getX()][tail.getY()] = 0;
				
				snake.offer(new Tail(nx, ny));
				map[nx][ny] = -1;
				headIndex[0] = nx;
				headIndex[1] = ny;
				
			}
		}	
	}
}