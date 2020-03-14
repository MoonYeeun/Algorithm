package not_solve_myself;

import java.util.ArrayList;
import java.util.HashMap;

// 2018 카카오 3차 자동완성
/*
Trie 자료구조 개념 !!!
'더 이상 분기점이 없는 지점 찾기'
*/
public class Programmers_autocomplete_trie {
    public static void main(String[] args) {
        String[] words = {"go","gone","guild"};
//        String[] words = {"abc","def","ghi","jklm"};
//        String[] words = {"word","war","warrior","world"};
        Trie trie = new Trie();
        // 트라이 자료구조에 각 word insert
        for(String word : words) {
            trie.insert(word);
        }
        int answer = 0;
        for(String word : words) {
            for(int i = 1 ; i <= word.length() ; i++) {
                answer++;
                String str = word.substring(0,i);
                int size = trie.findLeafs(str).size();
                // 더 이상 분기점 없는 경우
                if(size == 1) break;
            }
        }
        System.out.println(answer);
    }
    static class Trie {
        TrieNode root;

        public Trie() {
            root = new TrieNode(' ');
        }

        public void insert(String word) {
            TrieNode current = root;
            for(char c : word.toCharArray()) {

                if(current.getChild(c) != null) {
                    current = current.getChild(c);
                }
                else {
                    current = current.putChild(c);
                }
            }
            current.setLeaf(true);
        }
        // 리프노드 찾기
        public ArrayList<TrieNode> findLeafs(String word) {
            ArrayList<TrieNode> list = new ArrayList<>();

            TrieNode current = root;

            for(char c : word.toCharArray()) {
                if(current.getChild(c) != null) {
                    current = current.getChild(c);
                }
                else {
                    list.clear();
                    return list;
                }
            }
            if(current.isLeaf()) list.add(current);

            list.addAll(current.getAllLeaf());
            return list;
        }
    }
    static class TrieNode {
        private char c;
        private boolean isLeaf; // 리프노드 인지 아닌지 구분
        private HashMap<Character, TrieNode> children;

        TrieNode(char c) {
            this.c = c;
            children = new HashMap<>();
            isLeaf = false;
        }
        public boolean isLeaf() {
            return isLeaf;
        }
        public HashMap<Character, TrieNode> getChildren() {
            return children;
        }
        public void setLeaf(boolean leaf) {
            isLeaf = leaf;
        }

        // 입력 c에 해당하는 TrieNode 생성 후 Child로 추가
        public TrieNode putChild(char c) {
            TrieNode temp = new TrieNode(c);
            getChildren().put(c, temp);
            return temp;
        }
        // 현재 노드의 자식노드 중 입력값에 해당하는 값 리턴
        public TrieNode getChild(char c) {
            return getChildren().get(c);
        }
        // 현재 노드와 연결된 모든 자식노드 중 leaf 에 해당하는 노드 모두 리턴
        public ArrayList<TrieNode> getAllLeaf() {
            ArrayList<TrieNode> list = new ArrayList<>();
            for(Character c : getChildren().keySet()) {
                TrieNode value = getChildren().get(c);

                if(value.isLeaf()) {
                    list.add(value);
                }
                list.addAll(value.getAllLeaf());
            }
            return list;
        }
    }
}
