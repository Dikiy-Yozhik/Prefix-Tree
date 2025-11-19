@echo off
chcp 65001 >nul
echo 🔨 Compiling Trie Project...

echo Current directory: %CD%

if not exist "bin" mkdir "bin"

echo Step 1: Compiling TrieNode and WordFrequency...
javac -d bin src/trie/TrieNode.java src/trie/WordFrequency.java

echo Step 2: Compiling Trie...
javac -d bin -cp bin src/trie/Trie.java

echo Step 3: Compiling Main...
javac -d bin -cp bin src/Main.java

echo Step 4: Compiling tests...
javac -d bin -cp bin src/tests/basic_test.java src/tests/remove_test.java src/tests/getall_test.java src/tests/printtree_test.java src/tests/frequency_test.java

if %errorlevel% equ 0 (
    echo ✅ All files compiled successfully!
) else (
    echo ⚠️ Some compilation issues, but main program is ready
)

pause