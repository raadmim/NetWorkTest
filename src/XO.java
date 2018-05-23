import java.util.Scanner;

public class XO {

    private final static int EMPTY = -1, X = 0, O = 1;	//	Maghaadiri ke Safhe-e Bazimoon mitoone daashte baashe!
    private int currentTurn = X;	//	Farz Mikonim Avval Nobat-e X e!

    private boolean isGameFinished = false;	//Moshakhas mikone ke aayaa baaZ tamoom shode yaa na!

    private int[][] board;	//	Safhe-e Baazimoon!

    public XO () {
        System.out.println( "XO Started!" );
        this.board = new int[3][3];	//	Safhemoon ro new mikonim
        for ( int i = 0; i < 3; i++ )
            for ( int j = 0; j < 3; j++ )
                this.board[i][j] = XO.EMPTY;
//		KhooneHaa-e Safhemoon ro Baraabar ba meghdaari mikonim ke Be Onvaan-e Meghdaar-e khoone-e Khaali dar nazar gereftimesh!
    }

    //	X va Y ro migire, Khoone-e Mored-e nazar ro por mikone baa tavajjoh be nobat!
    public boolean addMohre( int x, int y ) {
        if ( this.board[y][x] != XO.EMPTY )	// Agar KhooneE ke taraf gofte bood khali nabood
            return false;	//Behesh ettelaa bede!
        this.board[y][x] = this.getCurrentTurn();	//Khoone-e Mored-e Nazar ro toosh chizi ro beriz ke nobateshe alaan!
        this.checkWinning();	//	Check kon bebin kasi borde yaa na!
        this.checkEven();	//Check kon bebin aaya mosaavi shode?
        this.switchTurn();	//	Nobat ro avaz kon!
        return true;	//	Hamechi baa movaffaghiat anjaam shode... be taraf begoo ke hamechiz OK boode!
    }

    private void checkWinning() {   // Check mikone bebine Aayaa kasi borde yaa na... barande ro chap mikone!

        for ( int i = 0; i < 3; i++ ) {
            // Check Kardan-e in ke aayaa kasi tooneste be soorat-e ofoghi bebare!
            if (this.board[i][0] == this.board[i][1] && this.board[i][1] == this.board[i][2] && this.board[i][0] != XO.EMPTY ) {
                this.setGameFinished( true );
                this.declareWinner(board[i][0]);
                return;
            }
            // check kardan-e in ke aayaa kasi tooneste be soorat-e amoodi bebare!
            if ( this.board[0][i] == this.board[1][i] && this.board[1][i] == this.board[2][i] && this.board[0][i] != XO.EMPTY ) {
                this.setGameFinished( true );
                this.declareWinner( board[0][i] );
                return;
            }
        }

        // check kardan-e in ke aayaa kasi tooneste be soorat-e orib bebare!
        if ( this.board[0][0] == this.board[1][1] && this.board[1][1] == this.board[2][2] && this.board[0][0] != XO.EMPTY ){
            this.setGameFinished( true );
            this.declareWinner( board[0][0] );
            return;
        }
        if ( this.board[2][0] == this.board[1][1] && this.board[1][1] == this.board[0][2] && this.board[2][0] != XO.EMPTY ) {
            this.setGameFinished( true );
            this.declareWinner( board[2][0] );
            return;
        }

    }

    private void checkEven() {	//check mikone bebine aaya baaZ mosaavi shode yaa na!
        for ( int i = 0; i < 3; i++ )
            for ( int j = 0; j < 3; j++ )
                if ( this.board[i][j] != XO.EMPTY )
                    return;
//		Age Khone-e khaliE vojood daasht, yani hanooz tamoom nashode baaZ!
        System.out.println( "MosaaV shoDd!" );
//		Age hich Khone-e KhalieE vojood nadaasht, yani baaZ mosaaV shode! In ke kasi borde yaa na ro check kardim ghablan!
    }

    //	Ye Khoone ro be onvaan-e Khoone-e barande migire... masalan Age X ro behesh voroodi bedim yani X borde!
    private void declareWinner( int winner ) {
        if ( winner == XO.X )
            System.out.println( "Winner is X!" );
        else if ( winner == XO.O )
            System.out.println( "Winner is O!" );
    }

    //	Nobat ro avaz mikone!
    private void switchTurn() {
        if ( this.getCurrentTurn() == XO.X )
            this.setCurrentTurn( XO.O );
        else this.setCurrentTurn( XO.X );
    }

    //	Safhe-e Baazi ro neshoon mide!
    public void printBoard() {
        System.out.println( this.toString() );
    }

    @Override
    public String toString() {	//Mohtaviaat-e safhe ro be soorat-e String barmigardoone!
        String returnValue = "";
        for ( int i = 0; i < 3; i++ ) {
            for ( int j = 0; j < 3; j++ ) {
                if ( this.board[i][j] == XO.EMPTY )
                    returnValue = returnValue.concat( "- " );
                else returnValue = returnValue.concat( this.getKhooneInChar( this.board[i][j] ) + " " );
            }
            returnValue = returnValue.concat( "\n" );
        }
        return returnValue;
    }

    public char getTurnInChar() {	//Maa nobat ro be soorat-e adad zakhire kardim... in taabe be soorat-e "X yaa Y" baresh migardoone!
        return this.getKhooneInChar( this.getCurrentTurn() );
    }

    public char getKhooneInChar( int khoone ) {	//Ye Khoone ro migire, be soorat-e character baresh migardoone!
        if ( khoone == XO.X )
            return 'X';
        else if ( khoone == XO.O )
            return 'O';
        else return '-';
    }

//	GetterHaa va SetterHaa

    public int getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }

    public boolean isGameFinished() {
        return isGameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        isGameFinished = gameFinished;
    }

}
