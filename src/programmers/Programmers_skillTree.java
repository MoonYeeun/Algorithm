package programmers;
//프로그래머스 스킬트리
public class Programmers_skillTree {
    public static void main(String[] args) {
        String skill= "CBD";
        String[] skill_trees = {"BACDE","CBADF","AECB","BDA"};
        int answer = 0;

        int tree_len = skill_trees.length;
        int skill_len = skill.length();

        for(int i = 0 ; i < tree_len ; i++){
            int len = skill_trees[i].length();
            int idx = 0;
            boolean check = true;
            for(int j = 0 ; j < len ; j++){
                if(idx == skill_len)
                    break;
                if(skill.indexOf(skill_trees[i].charAt(j))== -1)
                    continue;
                else if(skill.indexOf(skill_trees[i].charAt(j))== idx)
                    idx++;
                else{
                    check = false;
                    break;
                }
            }
            if(check)
                answer++;
        }
        System.out.println(answer);
    }
}
