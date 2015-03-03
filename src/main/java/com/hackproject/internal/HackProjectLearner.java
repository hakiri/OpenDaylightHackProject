package com.hackproject.internal;

import java.util.Map;

import org.opendaylight.controller.sal.core.Node;
import org.opendaylight.controller.sal.core.NodeConnector;
import org.opendaylight.controller.sal.core.Property;
import org.opendaylight.controller.sal.core.UpdateType;
import org.opendaylight.controller.sal.flowprogrammer.IFlowProgrammerService;
import org.opendaylight.controller.sal.packet.Ethernet;
import org.opendaylight.controller.sal.packet.IDataPacketService;
import org.opendaylight.controller.sal.packet.IListenDataPacket;
import org.opendaylight.controller.sal.packet.Packet;
import org.opendaylight.controller.sal.packet.PacketResult;
import org.opendaylight.controller.sal.packet.RawPacket;
import org.opendaylight.controller.switchmanager.IInventoryListener;
import org.opendaylight.controller.switchmanager.ISwitchManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hackproject.IHackProjectLearner;

public class HackProjectLearner implements IListenDataPacket,
		IHackProjectLearner, IInventoryListener {

	protected static final Logger logger = LoggerFactory
			.getLogger(HackProjectLearner.class);

	private IDataPacketService dataPacketService = null;
	private ISwitchManager switchManager = null;
	private IFlowProgrammerService programmer = null;

	@Override
	public PacketResult receiveDataPacket(RawPacket inPkt) {

		if (inPkt != null) {
			Packet formattedPak = this.dataPacketService
					.decodeDataPacket(inPkt);
			if (formattedPak instanceof Ethernet) {

				// Do your processing

			}
		}

		return PacketResult.IGNORED;

	}

	public void notifyNode(Node node, UpdateType type,
			Map<String, Property> propMap) {
		// Event will be received on Node addition/deletion here
		logger.info("Bonjour notifyNode " + type + node);

	}

	public void notifyNodeConnector(NodeConnector nodeConnector,
			UpdateType type, Map<String, Property> propMap) {

		// Event will be received on notifyNodeConnector addition/deletion here
		logger.info("Bonjour notifyNodeConnector " + type + nodeConnector);

	}

	@Override
	public void test() {
		// Do your stuff here
	}

	// OSGi Functions needed to create this service
	void start() {
		logger.info("Hacking Application Starting..");
	}

	void stop() {
		logger.info("Hacking Application Stopping");
	}

	void setDataPacketService(IDataPacketService s) {
		this.dataPacketService = s;
	}

	void unsetDataPacketService(IDataPacketService s) {
		if (this.dataPacketService == s) {
			this.dataPacketService = null;
		}
	}

	public void setFlowProgrammerService(IFlowProgrammerService s) {
		this.programmer = s;
	}

	public void unsetFlowProgrammerService(IFlowProgrammerService s) {
		if (this.programmer == s) {
			this.programmer = null;
		}
	}

	void setSwitchManager(ISwitchManager s) {
		logger.debug("SwitchManager set");
		this.switchManager = s;
	}

	void unsetSwitchManager(ISwitchManager s) {
		if (this.switchManager == s) {
			logger.debug("SwitchManager removed!");
			this.switchManager = null;
		}
	}

}