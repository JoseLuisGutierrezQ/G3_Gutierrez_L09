public void bfs(E v) {
    Vertex<E> start = searchVertex(v);
    if (start == null) return;

    Queue<Vertex<E>> queue = new LinkedList<>();
    Set<Vertex<E>> visited = new HashSet<>();
    queue.add(start);
    visited.add(start);

    while (!queue.isEmpty()) {
        Vertex<E> current = queue.poll();
        System.out.print(current.getData() + " ");
        Node<Edge<E>> edgeNode = current.listAdj.getHead();
        while (edgeNode != null) {
            Vertex<E> neighbor = edgeNode.getData().refDest;
            if (!visited.contains(neighbor)) {
                visited.add(neighbor);
                queue.add(neighbor);
            }
            edgeNode = edgeNode.getNext();
        }
    }
}



public ArrayList<E> bfsPath(E v, E z) {
    Vertex<E> start = searchVertex(v);
    Vertex<E> end = searchVertex(z);
    ArrayList<E> path = new ArrayList<>();
    if (start == null || end == null) return path;

    Queue<Vertex<E>> queue = new LinkedList<>();
    Map<Vertex<E>, Vertex<E>> parent = new HashMap<>();
    Set<Vertex<E>> visited = new HashSet<>();
    queue.add(start);
    visited.add(start);
    parent.put(start, null);

    boolean found = false;
    while (!queue.isEmpty() && !found) {
        Vertex<E> current = queue.poll();
        if (current.equals(end)) {
            found = true;
            break;
        }
        Node<Edge<E>> edgeNode = current.listAdj.getHead();
        while (edgeNode != null) {
            Vertex<E> neighbor = edgeNode.getData().refDest;
            if (!visited.contains(neighbor)) {
                visited.add(neighbor);
                parent.put(neighbor, current);
                queue.add(neighbor);
            }
            edgeNode = edgeNode.getNext();
        }
    }

    if (found) {
        Vertex<E> node = end;
        while (node != null) {
            path.add(0, node.getData());
            node = parent.get(node);
        }
    }
    return path;
}