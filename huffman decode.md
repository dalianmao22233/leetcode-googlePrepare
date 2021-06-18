```
void decode(String s, Node root) {
        
        StringBuilder output = new StringBuilder();
        Node base = root;
        while (!s.isEmpty()) {
            if (s.charAt(0) == '1') {
                base = base.right;
                s = s.substring(1);
            } else {
                base = base.left;
                s = s.substring(1);
            }
            if (base.left == null && base.right == null) {
                output.append(base.data);
                base = root;
            }
        }
        System.out.println(output.toString());
       
    }
```
