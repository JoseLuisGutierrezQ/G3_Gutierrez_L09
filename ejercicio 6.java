// Formal
public void printFormal() {
    System.out.println("V = {");
    for (Vertex<E> v : listVertex) {
        System.out.println("  " + v.getData());
    }
    System.out.println("}");
    System.out.println("A = {");
    Set<String> printedEdges = new HashSet<>();
    for (Vertex<E> v : listVertex) {
        Node<Edge<E>> edgeNode = v.listAdj.getHead();
        while (edgeNode != null) {
            Edge<E> edge = edgeNode.getData();
            String edgeStr = "(" + v.getData() + "," + edge.refDest.getData() + ")";
            String reverseEdgeStr = "(" + edge.refDest.getData() + "," + v.getData() + ")";
            if (!printedEdges.contains(edgeStr) && !printedEdges.contains(reverseEdgeStr)) {
                System.out.println("  " + edgeStr);
                printedEdges.add(edgeStr);
            }
            edgeNode = edgeNode.getNext();
        }
    }
    System.out.println("}");
}

// Lista de adyacencia
public void printAdjacencyList() {
    for (Vertex<E> v : listVertex) {
        System.out.print(v.getData() + " -> ");
        Node<Edge<E>> edgeNode = v.listAdj.getHead();
        while (edgeNode != null) {
            System.out.print(edgeNode.getData().refDest.getData() + " ");
            edgeNode = edgeNode.getNext();
        }
        System.out.println();
    }
}

// Matriz de adyacencia
public void printAdjacencyMatrix() {
    int n = listVertex.size();
    int[][] matrix = new int[n][n];
    Map<E, Integer> vertexIndex = new HashMap<>();
    int index = 0;
    for (Vertex<E> v : listVertex) {
        vertexIndex.put(v.getData(), index++);
    }
    for (Vertex<E> v : listVertex) {
        int i = vertexIndex.get(v.getData());
        Node<Edge<E>> edgeNode = v.listAdj.getHead();
        while (edgeNode != null) {
            int j = vertexIndex.get(edgeNode.getData().refDest.getData());
            matrix[i][j] = 1;
            matrix[j][i] = 1;
            edgeNode = edgeNode.getNext();
        }
    }
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            System.out.print(matrix[i][j] + " ");
        }
        System.out.println();
    }
}