#!/bin/bash
echo "🔨 Compiling Trie Project..."

cd ..
mkdir -p bin

javac -d bin src/trie/*.java src/tests/*.java src/Main.java

if [ $? -eq 0 ]; then
    echo "✅ Compilation successful!"
else
    echo "❌ Compilation failed!"
    exit 1
fi