const editor = CodeMirror(document.getElementById('editor'), {
    value: "public class Main {\n   public static void main(String[] args) {\n      System.out.println(\"Hello Codetok!\");\n   }\n}",
    mode: "text/x-java",
    theme: "dracula",
    lineNumbers: true,
    tabSize: 4
});

const form = document.getElementById("codeForm");
const hiddenInput = document.getElementById("userCode");

form.addEventListener("submit", (e) => {
    const code = editor.getValue();
    hiddenInput.value = code
});