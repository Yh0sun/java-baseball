package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Application {
    static class Baseball {
        static List<Integer> computerNum;
        static int[] computerNumCheck = new int[10];
        static int[] strikeResult = new int[3];

        public void clear(){
            computerNumCheck=new int[10];
        }
        public void strikeResultClear(){
            for(int i=0;i<3;i++){
                strikeResult[i]=0;
            }
        }
        public boolean startGame(){
            computerNum = makeComputerNum();

            while(true) {
                System.out.print("숫자를 입력해주세요 : ");
                String input = Console.readLine();
                List<Integer> inputNum = toInputNum(input);

                strikeResultClear();

                int strikeCnt = strike(inputNum);
                int ballCnt = ball(inputNum);
                if (resultPrint(ballCnt, strikeCnt)) return true;
            }
        }

        public List<Integer> makeComputerNum() {
            List<Integer> computerNum = new ArrayList<>();

            while (computerNum.size() < 3) {
                int randomNumber = Randoms.pickNumberInRange(1, 9);
                if (!computerNum.contains(randomNumber)) {
                    computerNum.add(randomNumber);
                    computerNumCheck[randomNumber] = 1;
                }
            }
            return computerNum;
        }

        public List<Integer> toInputNum(String input) {
            List<Integer> inputNum = new ArrayList<>();
            String[] inputStr = input.split("");

            for (int i = 0; i < 3; i++) {
                inputNum.add(Integer.parseInt(inputStr[i]));
            }
            return inputNum;
        }

        public int strike(List<Integer> inputNum) {
            int strikeCnt = 0;
            for (int i = 0; i < 3; i++) {
                if (Integer.compare(computerNum.get(i), inputNum.get(i)) == 0) {
                    strikeCnt++;
                    strikeResult[i] = 1;
                }
            }
            return strikeCnt;
        }

        public int ball(List<Integer> inputNum) {
            int ballCnt = 0;
            for (int i = 0; i < 3; i++) {
                if (strikeResult[i] != 1 && computerNumCheck[inputNum.get(i)] == 1) ballCnt++;
            }
            return ballCnt;
        }

        public boolean resultPrint(int ballCnt, int strikeCnt) {
            if (strikeCnt == 3) {
                System.out.println("3스트라이크");
                System.out.print("3개의 숫자를 모두 맞히셨습니다! ");
                return true;
            } else if (strikeCnt == 0 && ballCnt == 0) {
                System.out.println("낫싱");
                return false;
            } else {
                if (ballCnt != 0) System.out.print(String.format("%d볼 ", ballCnt));
                if (strikeCnt != 0) System.out.print(String.format("%d스트라이크 ", strikeCnt));
                System.out.println("");
                return false;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("숫자 야구 게임을 시작합니다.");
        Baseball baseball = new Baseball();
        int nextGame = 1;

        while (nextGame == 1) {
            baseball.clear();
            if (baseball.startGame()) {
                System.out.println("게임종료");
                System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
                String input = Console.readLine();
                nextGame = Integer.parseInt(input);
            }
        }
    }
}
