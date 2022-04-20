import java.util.*;
import java.io.*;

class Main {
	public static int N;
	public static int result;
	public static int[][] map;
	public static int[] direction;
	public static int[] dx = { -1, 0, 1, 0 };
	public static int[] dy = { 0, 1, 0, -1 };
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		direction = new int[5];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		DFS(5, 0);
		bw.write(result + "\n");
		bw.flush();
		bw.close();
	}
	
	public static void DFS(int end, int depth) {
		if (depth == end) {
			confirm();
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			direction[depth] = i;
			DFS(end, depth + 1);
		}
	}
	
	public static void confirm() {
		int[][] copyMap = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
		
		for (int i = 0; i < 5; i++) {
			boolean[][] check = new boolean[N][N];
			
			// North
			if (direction[i] == 0) {
				for (int row = 0; row < N; row++) {
					for (int col = 0; col < N; col++) {
						move(copyMap, check, row, col, direction[i]);
					}
				}
			}
			
			// East
			if (direction[i] == 1) {
				for (int row = 0; row < N; row++) {
					for (int col = N - 1; col >= 0; col--) {
						move(copyMap, check, row, col, direction[i]);
					}
				}
			}
			
			// South
			if (direction[i] == 2) {
				for (int row = N - 1; row >= 0; row--) {
					for (int col = 0; col < N; col++) {
						move(copyMap, check, row, col, direction[i]);
					}
				}
			}
			
			// West
			if (direction[i] == 3) {
				for (int row = 0; row < N; row++) {
					for (int col = 0; col < N; col++) {
						move(copyMap, check, row, col, direction[i]);
					}
				}
			}
		}
		
		int maxValue = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				maxValue = (maxValue < copyMap[i][j]) ? copyMap[i][j] : maxValue;
			}
		}
		
		result = (result < maxValue) ? maxValue : result;
		return;
	}
	
	public static void move(int[][] copyMap, boolean[][] check, int row, int col, int d) {
		if (copyMap[row][col] == 0) return;
		
		while(true) {
			
			int nRow = row + dx[d];
			int nCol = col + dy[d];
			
			// Check Range
			if (nRow >= N || nRow < 0 || nCol >= N || nCol < 0) return;
			
			// Check value
			if (copyMap[nRow][nCol] == copyMap[row][col]) {
				if (!check[nRow][nCol]) {
					copyMap[row][col] = 0;
					copyMap[nRow][nCol] *= 2;
					check[nRow][nCol] = true;
				}
				return;
			}
			else {
				if (copyMap[nRow][nCol] == 0) {
					copyMap[nRow][nCol] = copyMap[row][col];
					copyMap[row][col] = 0;
					row = nRow;
					col = nCol;
				}
				else {
					return;
				}
			}
		}
	}
	
	
}