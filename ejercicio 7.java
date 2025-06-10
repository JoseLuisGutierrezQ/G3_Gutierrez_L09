// isDirectedPath()
public boolean isDirectedPath() {
    int startCount = 0;
    int endCount = 0;
    for (Vertex<E> v : listVertex) {
        int outDegree = v.listAdj.size();
        int inDegree = 0;
        for (Vertex<E> u : listVertex) {
            Node<Edge<E>> edgeNode = u.listAdj.getHead();
            while (edgeNode != null) {
                if (edgeNode.getData().refDest.equals(v)) {
                    inDegree++;
                }
                edgeNode = edgeNode.getNext();
            }
        }
        if (outDegree == 1 && inDegree == 0) startCount++;
        else if (outDegree == 0 && inDegree == 1) endCount++;
        else if (outDegree != 1 || inDegree != 1) return false;
    }
    return startCount == 1 && endCount == 1;
}

// isDirectedCycle()
public boolean isDirectedCycle() {
    for (Vertex<E> v : listVertex) {
        int outDegree = v.listAdj.size();
        int inDegree = 0;
        for (Vertex<E> u : listVertex) {
            Node<Edge<E>> edgeNode = u.listAdj.getHead();
            while (edgeNode != null) {
                if (edgeNode.getData().refDest.equals(v)) {
                    inDegree++;
                }
                edgeNode = edgeNode.getNext();
            }
        }
        if (outDegree != 1 || inDegree != 1) return false;
    }
    return true;
}