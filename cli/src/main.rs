mod compiler;
use compiler::LnwParser;
use clap::{Parser, Subcommand};
use anyhow::Result;

#[derive(Parser)]
#[command(author, version, about, long_about = None)]
struct Cli {
    #[command(subcommand)]
    command: Commands,
}

#[derive(Subcommand)]
enum Commands {
    /// Compile an .lnw or .epp file
    Compile {
        #[arg(short, long)]
        file: String,
    },
    /// Execute a Linux command on Windows
    Run {
        #[arg(short, long)]
        cmd: String,
    },
    /// Manage Linux-like utilities
    Pkg {
        #[command(subcommand)]
        action: PkgAction,
    },
}

#[derive(Subcommand)]
enum PkgAction {
    Install { name: String },
    List,
    Remove { name: String },
}

fn main() -> Result<()> {
    let cli = Cli::parse();

    match &cli.command {
        Commands::Compile { file } => {
            LnwParser::parse(file)?;
        }
        Commands::Run { cmd } => {
            println!("Running command: {}", cmd);
            // TODO: Implement interpreter
        }
        Commands::Pkg { action } => {
            match action {
                PkgAction::Install { name } => println!("Installing package: {}", name),
                PkgAction::List => println!("Listing packages"),
                PkgAction::Remove { name } => println!("Removing package: {}", name),
            }
        }
    }

    Ok(())
}
