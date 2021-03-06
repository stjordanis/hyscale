/**
 * Copyright 2019 Pramati Prism, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.hyscale.controller.commands;

import org.springframework.stereotype.Component;
import picocli.CommandLine;

/**
 *  This class executes  'hyscale get service' command
 *  It is a sub-command of the 'hyscale get ' command
 *  @see HyscaleGetCommand .
 *  Every command/sub-command has to implement the Runnable so that
 *  whenever the command is executed the {@link #run()}
 *  method will be invoked
 *
 * The sub-commands of are handled by @Command annotation
 *
 */
@CommandLine.Command(name = "service", subcommands = { HyscaleServiceLogsCommand.class,
		HyscaleServiceStatusCommand.class }, description = "Performs action on the service")
@Component
public class HyscaleGetServiceCommand implements Runnable {

	@CommandLine.Option(names = {"-h", "--help"}, usageHelp = true, description = "Displays the help information of the specified command")
	private boolean helpRequested = false;

	/**
	 * Executes the 'hyscale get service' command
	 * Provides usage of this command to the user
	 */
	@Override
	public void run() {
		new CommandLine(new HyscaleGetServiceCommand()).usage(System.out);
	}
}
