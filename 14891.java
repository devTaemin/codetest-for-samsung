class Main {
	public static int K;
	public static int[] A, B, C, D;
	public static int[] Left, Right, Rotation, Target, Direction;
	
	public static int Calculate() {
		int result = 0;
		int first = (Right[1] - 2 >= 0) ? (Right[1] - 2) : (Right[1] + 8 - 2);
		int second = (Right[2] - 2 >= 0) ? (Right[2] - 2) : (Right[2] + 8 - 2);
		int third = (Right[3] - 2 >= 0) ? (Right[3] - 2) : (Right[3] + 8 - 2);
		int fourth = (Right[4] - 2 >= 0) ? (Right[4] - 2) : (Right[4] + 8 - 2);
		
		if (A[first] == 1) result += 1;
		if (B[second] == 1) result += 2;
		if (C[third] == 1) result += 4;
		if (D[fourth] == 1) result += 8;
		
		return result;
	}
	
	public static int Move(int d, int t) {
		int result = t;
		
		if (d == 1) {
			if (t == 0) result = 7;
			else result = t - 1;
		}
		
		if (d == -1) {
			if (t == 7) result = 0;
			else result = t + 1;
		}
		
		return result;
	}
	
	public static void Rotate(int target, int d) {
		Direction = new int[5];
		
		if (target == 1) {
			if (d == 1) {
				Direction[1] = 1;
				if (A[Right[1]] == B[Left[2]]);
				else {
					Direction[2] = -1;
					if (B[Right[2]] == C[Left[3]]);
					else {
						Direction[3] = 1;
						if (C[Right[3]] == D[Left[4]]);
						else {
							Direction[4] = -1;
						}
					}
				}
			}
			
			if (d == -1) {
				Direction[1] = -1;
				if (A[Right[1]] == B[Left[2]]);
				else {
					Direction[2] = 1;
					if (B[Right[2]] == C[Left[3]]);
					else {
						Direction[3] = -1;
						if (C[Right[3]] == D[Left[4]]);
						else {
							Direction[4] = 1;
						}
					}
				}
			}
		}
		
		if (target == 2) {
			if (d == 1) {
				Direction[2] = 1;
				if (A[Right[1]] == B[Left[2]]);
				else {
					Direction[1] = -1;
				}
				
				if (B[Right[2]] == C[Left[3]]);
				else {
					Direction[3] = -1;
					if (C[Right[3]] == D[Left[4]]);
					else {
						Direction[4] = 1;
					}
				}
			}
			
			if (d == -1) {
				Direction[2] = -1;
				if (A[Right[1]] == B[Left[2]]);
				else {
					Direction[1] = 1;
				}
				
				if (B[Right[2]] == C[Left[3]]);
				else {
					Direction[3] = 1;
					if (C[Right[3]] == D[Left[4]]);
					else {
						Direction[4] = -1;
					}
				}
			}
		}
		
		if (target == 3) {
			if (d == 1) {
				Direction[3] = 1;
				if (C[Right[3]] == D[Left[4]]);
				else {
					Direction[4] = -1;
				}
				
				if (B[Right[2]] == C[Left[3]]);
				else {
					Direction[2] = -1;
					if (A[Right[1]] == B[Left[2]]);
					else {
						Direction[1] = 1;
					}
				}
			}
			
			if (d == -1) {
				Direction[3] = -1;
				if (C[Right[3]] == D[Left[4]]);
				else {
					Direction[4] = 1;
				}
				
				if (B[Right[2]] == C[Left[3]]);
				else {
					Direction[2] = 1;
					if (A[Right[1]] == B[Left[2]]);
					else {
						Direction[1] = -1;
					}
				}
			}
		}
		
		if (target == 4) {
			if (d == 1) {
				Direction[4] = 1;
				if (C[Right[3]] == D[Left[4]]);
				else {
					Direction[3] = -1;
					if (B[Right[2]] == C[Left[3]]);
					else {
						Direction[2] = 1;
						if (A[Right[1]] == B[Left[2]]);
						else {
							Direction[1] = -1;
						}
					}
				}
			}
			
			if (d == -1) {
				Direction[4] = -1;
				if (C[Right[3]] == D[Left[4]]);
				else {
					Direction[3] = 1;
					if (B[Right[2]] == C[Left[3]]);
					else {
						Direction[2] = -1;
						if (A[Right[1]] == B[Left[2]]);
						else {
							Direction[1] = 1;
						}
					}
				}
			}
		}
		
		// Move
		for (int i = 1; i <= 4; i++) {
			Left[i] = Move(Direction[i], Left[i]);
			Right[i] = Move(Direction[i], Right[i]);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		A = new int[8];
		B = new int[8];
		C = new int[8];
		D = new int[8];
		
		char[] first = br.readLine().toCharArray();
		for (int i = 0; i < 8; i++) {
			A[i] = first[i] - '0';
		}
		
		char[] second = br.readLine().toCharArray();
		for (int i = 0; i < 8; i++) {
			B[i] = second[i] - '0';
		}
		
		char[] third = br.readLine().toCharArray();
		for (int i = 0; i < 8; i++) {
			C[i] = third[i] - '0';
		}
		
		char[] fourth = br.readLine().toCharArray();
		for (int i = 0; i < 8; i++) {
			D[i] = fourth[i] - '0';
		}
		
		K = Integer.parseInt(br.readLine());
		Left = new int[] { 0, 6, 6, 6, 6 };
		Right = new int[] { 0, 2, 2, 2, 2 };
		Target = new int[K];
		Rotation = new int[K];
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			Target[i] = Integer.parseInt(st.nextToken());
			Rotation[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < K; i++) {
			Rotate(Target[i], Rotation[i]);
		}
		
		int result = Calculate();
		bw.write(result + "\n");
		bw.flush();
		bw.close();
		
	}
}