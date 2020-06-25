package produktionscode;

public class UndoCommand extends Parkhaus implements IF_Command{
	
	IF_Parkhaus p;
	
	public UndoCommand(IF_Parkhaus p){
		this.p = p;
	}
	
	
	
	@Override
	public void exec() {
		p.undo();
	}
	
}
