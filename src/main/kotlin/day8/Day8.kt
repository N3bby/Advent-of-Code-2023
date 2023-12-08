package day8

import util.leastCommonMultiple
import java.lang.IllegalStateException

enum class Instruction {
    LEFT,
    RIGHT
}

typealias NodeId = String

data class Node(val id: NodeId, private val left: NodeId, private val right: NodeId) {

    fun isFirstNode(): Boolean = id == "AAA"
    fun isFirstNodeForGhost(): Boolean = id.endsWith("A")

    fun isFinalNode(): Boolean = id == "ZZZ"
    fun isFinalNodeForGhost(): Boolean = id.endsWith("Z")

    fun getNextNode(instruction: Instruction): NodeId {
        return when (instruction) {
            Instruction.LEFT -> left
            Instruction.RIGHT -> right
        }
    }

}

private data class NodeAndInstruction(val node: Node, val instructionIndex: Int)

private data class Cycle(val start: Int, val length: Int, val nodes: MutableList<NodeAndInstruction>) {
    val endNodeIndex = nodes.indexOfFirst { it.node.isFinalNodeForGhost() }
}

data class Network(private val nodes: List<Node>, private val instructions: List<Instruction>) {

    private val nodesById = nodes.associateBy { it.id }

    fun countNodesToTraverse(): Int {
        var nodesTraversed = 0
        var instructionIndex = 0
        var currentNode = nodes.find { it.isFirstNode() } ?: throw IllegalStateException("Could not find first node")

        while (!currentNode.isFinalNode()) {
            val instruction = instructions[instructionIndex]
            val nextNodeId = currentNode.getNextNode(instruction)

            currentNode = nodesById[nextNodeId]!!
            instructionIndex = (instructionIndex + 1) % instructions.size
            nodesTraversed++
        }

        return nodesTraversed
    }

    private fun findCycle(startNode: Node): Cycle {
        val nodesVisited = mutableListOf(NodeAndInstruction(startNode, 0))
        var instructionIndex = 0
        var currentNode = startNode

        while (true) {
            val instruction = instructions[instructionIndex]
            val nextNode = nodesById[currentNode.getNextNode(instruction)]!!

            val nodeAndInstruction = NodeAndInstruction(nextNode, (instructionIndex + 1) % instructions.size)
            if(nodeAndInstruction in nodesVisited) {
                // Loop detected
                val cycleStart = nodesVisited.indexOf(nodeAndInstruction)
                val cycleLength = nodesVisited.size - cycleStart

                return Cycle(cycleStart, cycleLength, nodesVisited.subList(cycleStart, nodesVisited.size))
            }

            nodesVisited.add(nodeAndInstruction)
            currentNode = nextNode
            instructionIndex = (instructionIndex + 1) % instructions.size
        }
    }

    private fun findLengthOfCyclesAsGhost(): List<Int> {
        val startNodes = nodes.filter { it.isFirstNodeForGhost() }
        val cycles = startNodes.map { node -> findCycle(node) }
        return cycles.map { it.length }
    }

    /**
     * This only works because of the nature of our data
     * If this is run on a completely random input, it will not produce a correct result
     */
    fun countNodesToTraverseAsGhost(): Long {
        val routeLengths = findLengthOfCyclesAsGhost().map { it.toLong() }
        return leastCommonMultiple(routeLengths)
    }
}

