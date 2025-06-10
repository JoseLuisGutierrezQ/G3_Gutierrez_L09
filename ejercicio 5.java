public boolean isCompleteGraph() {
    int n = listVertex.size();
    for (Vertex<E> v : listVertex) {
        if (v.listAdj.size() != n - 1) return false;
    }
    return true;
}

public boolean isCycleGraph() {
    if (listVertex.size() < 3) return false;
    for (Vertex<E> v : listVertex) {
        if (v.listAdj.size() != 2) return false;
    }
    return isConexo();
}