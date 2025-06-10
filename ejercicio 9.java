// isIsomorphic(GraphLink<E> other)
public boolean isIsomorphic(GraphLink<E> other) {
    if (this.listVertex.size() != other.listVertex.size()) return false;
    if (this.listVertex.isEmpty()) return true;
    return checkIsomorphism(this, other, new HashMap<>(), 0);
}

private boolean checkIsomorphism(GraphLink<E> g1, GraphLink<E> g2, Map<E, E> mapping, int index) {
    if (index == g1.listVertex.size()) return true;
    Vertex<E> v1 = g1.listVertex.get(index);
    for (Vertex<E> v2 : g2.listVertex) {
        if (!mapping.containsValue(v2.getData())) {
            mapping.put(v1.getData(), v2.getData());
            if (isIsomorphicSoFar(g1, g2, mapping) && 
                checkIsomorphism(g1, g2, mapping, index + 1)) {
                return true;
            }
            mapping.remove(v1.getData());
        }
    }
    return false;
}

private boolean isIsomorphicSoFar(GraphLink<E> g1, GraphLink<E> g2, Map<E, E> mapping) {
    for (Vertex<E> v1 : g1.listVertex) {
        if (mapping.containsKey(v1.getData())) {
            Vertex<E> v2 = g2.searchVertex(mapping.get(v1.getData()));
            if (v2 == null) return false;
            Set<E> neighbors1 = new HashSet<>();
            Node<Edge<E>> edgeNode = v1.listAdj.getHead();
            while (edgeNode != null) {
                neighbors1.add(edgeNode.getData().refDest.getData());
                edgeNode = edgeNode.getNext();
            }
            Set<E> neighbors2 = new HashSet<>();
            edgeNode = v2.listAdj.getHead();
            while (edgeNode != null) {
                neighbors2.add(edgeNode.getData().refDest.getData());
                edgeNode = edgeNode.getNext();
            }
            if (neighbors1.size() != neighbors2.size()) return false;
            for (E neighbor1 : neighbors1) {
                if (!mapping.containsKey(neighbor1) || !neighbors2.contains(mapping.get(neighbor1))) {
                    return false;
                }
            }
        }
    }
    return true;
}

// isPlanar()
public boolean isPlanar() {
    return listVertex.size() <= 4 || 
           (listVertex.size() >= 3 && 
            countEdges() <= 3 * listVertex.size() - 6);
}

private int countEdges() {
    int count = 0;
    for (Vertex<E> v : listVertex) {
        count += v.listAdj.size();
    }
    return count / 2;
}

// isAutoComplementario()
public boolean isAutoComplementario() {
    GraphLink<E> complement = new GraphLink<>();
    for (Vertex<E> v : listVertex) {
        complement.insertVertex(v.getData());
    }
    for (int i = 0; i < listVertex.size(); i++) {
        Vertex<E> v1 = listVertex.get(i);
        for (int j = i + 1; j < listVertex.size(); j++) {
            Vertex<E> v2 = listVertex.get(j);
            if (!searchEdge(v1.getData(), v2.getData())) {
                complement.insertEdge(v1.getData(), v2.getData());
            }
        }
    }
    return this.isIsomorphic(complement);
}