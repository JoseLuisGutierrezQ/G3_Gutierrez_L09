public void insertEdgeWeight(E verOri, E verDes, int weight) {
    Vertex<E> origin = searchVertex(verOri);
    Vertex<E> destination = searchVertex(verDes);
    if (origin != null && destination != null) {
        origin.listAdj.insertLast(new Edge<>(destination, weight));
        destination.listAdj.insertLast(new Edge<>(origin, weight));
    }
}


public ArrayList<E> shortPath(E v, E z) {
    Vertex<E> start = searchVertex(v);
    Vertex<E> end = searchVertex(z);
    ArrayList<E> path = new ArrayList<>();
    if (start == null || end == null) return path;

    PriorityQueue<VertexDist<E>> pq = new PriorityQueue<>();
    Map<Vertex<E>, Integer> distances = new HashMap<>();
    Map<Vertex<E>, Vertex<E>> previous = new HashMap<>();
    for (Vertex<E> vertex : listVertex) {
        distances.put(vertex, Integer.MAX_VALUE);
    }
    distances.put(start, 0);
    pq.add(new VertexDist<>(start, 0));

    while (!pq.isEmpty()) {
        VertexDist<E> current = pq.poll();
        if (current.vertex.equals(end)) break;

        Node<Edge<E>> edgeNode = current.vertex.listAdj.getHead();
        while (edgeNode != null) {
            Edge<E> edge = edgeNode.getData();
            Vertex<E> neighbor = edge.refDest;
            int newDist = distances.get(current.vertex) + edge.weight;
            if (newDist < distances.get(neighbor)) {
                distances.put(neighbor, newDist);
                previous.put(neighbor, current.vertex);
                pq.add(new VertexDist<>(neighbor, newDist));
            }
            edgeNode = edgeNode.getNext();
        }
    }

    Vertex<E> node = end;
    while (node != null) {
        path.add(0, node.getData());
        node = previous.get(node);
    }
    return path;
}


public boolean isConexo() {
    if (listVertex.isEmpty()) return true;
    Set<Vertex<E>> visited = new HashSet<>();
    Stack<Vertex<E>> stack = new Stack<>();
    stack.push(listVertex.getHead().getData());

    while (!stack.isEmpty()) {
        Vertex<E> current = stack.pop();
        if (!visited.contains(current)) {
            visited.add(current);
            Node<Edge<E>> edgeNode = current.listAdj.getHead();
            while (edgeNode != null) {
                Vertex<E> neighbor = edgeNode.getData().refDest;
                stack.push(neighbor);
                edgeNode = edgeNode.getNext();
            }
        }
    }
    return visited.size() == listVertex.size();
}