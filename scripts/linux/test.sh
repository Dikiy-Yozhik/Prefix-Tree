#!/bin/bash
echo "🧪 Running All Tests..."

cd ..
echo "=== Basic Operations Test ==="
java -cp bin tests.basic_test

echo ""
echo "=== Remove Test ==="
java -cp bin tests.remove_test

echo ""
echo "=== GetAll Test ==="
java -cp bin tests.getall_test

echo ""
echo "=== PrintTree Test ==="
java -cp bin tests.printtree_test

echo ""
echo "=== Frequency Test ==="
java -cp bin tests.frequency_test

echo ""
echo "✅ All tests completed!"