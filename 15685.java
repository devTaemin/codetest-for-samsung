class Main {
	public static int N;
	public static int Result;
	public static int CurX, CurY;
	public static int X, Y, D, G;
	public static int gen_loop;
	
	public static int[][] Map = new int[101][101]; // [0 ~ 100] [0 ~ 100]
	
	// East North West South
	public static int[] Dy = { 0, -1, 0, 1 };
	public static int[] Dx = { 1, 0, -1, 0 };
	
	// Reverse direction
	// East -> West, North -> South, West -> East, South -> North
	public static int[] Dr = { 2, 3, 0, 1 };
	
	// Rotate direction
	// East -> South, North -> East, West -> North, South -> West
	public static int[] Drt = { 3, 0, 1, 2 };
	
	public static ArrayList<Integer> Direction;
	public static Stack<Integer> Stack;
	public static Iterator<Integer> iter;
	
	public static void Count() {
		for (int i = 0; i <= 100; i++) {
			for (int j = 0; j <= 100; j++) {
				int x1 = i;
				int x2 = i;
				int x3 = i + 1;
				int x4 = i + 1;
				int y1 = j;
				int y2 = j + 1;
				int y3 = j;
				int y4 = j + 1;
				
				// Range
				if (x1 < 0 || x1 > 100 || y1 < 0 || y1 > 100 ||
					x2 < 0 || x2 > 100 || y2 < 0 || y2 > 100 ||
					x3 < 0 || x3 > 100 || y3 < 0 || y3 > 100 ||
					x4 < 0 || x4 > 100 || y4 < 0 || y4 > 100) {
					continue;
				}
				
				// Check
				if (Map[x1][y1] == 1 &&
					Map[x2][y2] == 1 &&
					Map[x3][y3] == 1 &&
					Map[x4][y4] == 1) {
					Result++;
				}
			}
		}
	}
	
	public static void Run(int generation) {
		
		while(gen_loop < generation) {
			// Stack에 넣기
			iter = Direction.iterator();
			while(iter.hasNext()) {
				Stack.push(iter.next());
			}
			
			boolean isRange = true;
			while(!Stack.isEmpty()) {
				int d = Stack.pop();
				int nx = CurX + Dx[Drt[d]];
				int ny = CurY + Dy[Drt[d]];
				
				if (nx < 0 || nx > 100 || ny < 0 || ny > 100) {
					isRange = false;
					break;
				}
				
				Direction.add(Dr[Drt[d]]);
				CurX = nx;
				CurY = ny;
				Map[CurY][CurX] = 1;
			}
			
			if (!isRange) break;
			
			gen_loop++;
		}
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		Result = 0;
		
		for (int i = 0; i < N; i++) {
			X = sc.nextInt();
			Y = sc.nextInt();
			D = sc.nextInt();
			G = sc.nextInt();
			
			int ex = X + Dx[D];
			int ey = Y + Dy[D];
			
			Direction = new ArrayList<Integer>();
			Stack = new Stack<Integer>();
			Map[Y][X] = 1;
			Map[ey][ex] = 1;
			Direction.add(Dr[D]);
			gen_loop = 0;
			CurX = ex;
			CurY = ey;
			
			Run(G);
		}
		
		Count();
		System.out.println(Result);
		sc.close();
		
	}
}