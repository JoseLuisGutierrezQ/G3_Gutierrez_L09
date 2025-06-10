public void insertVertex(V data) {
    if (!secVertex.stream().anyMatch(v -> v.info.equals(data))) {
        secVertex.add(new VertexObj<>(data, secVertex.size()));
    }
}

public void insertEdge(V v1, V v2, E info) {
    VertexObj<V, E> vert1 = secVertex.stream().filter(v -> v.info.equals(v1)).findFirst().orElse(null);
    VertexObj<V, E> vert2 = secVertex.stream().filter(v -> v.info.equals(v2)).findFirst().orElse(null);
    if (vert1 != null && vert2 != null) {
        secEdge.add(new EdgeObj<>(vert1, vert2, info, secEdge.size()));
    }
}

public boolean searchEdge(V v1, V v2) {
    return secEdge.stream().anyMatch(e -> 
        (e.endVertex1.info.equals(v1) && e.endVertex2.info.equals(v2)) ||
        (e.endVertex1.info.equals(v2) && e.endVertex2.info.equals(v1))
    );
}


