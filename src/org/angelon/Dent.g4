lexer grammar Dent;

@lexer::members {
	private boolean pendingDent = true;
	private int indentCount = 0;
	private java.util.LinkedList<Token> tokenQueue = new java.util.LinkedList<>();
	private java.util.Stack<Integer> indentStack = new java.util.Stack<>();
	private Token initialIndentToken = null;
	private int getSavedIndent() { return indentStack.isEmpty() ? 0 : indentStack.peek(); }

	private CommonToken createToken(int type, String text, Token next) {
		CommonToken token = new CommonToken(type, text);
		if (null != initialIndentToken) {
			token.setStartIndex(initialIndentToken.getStartIndex());
			token.setLine(initialIndentToken.getLine());
			token.setCharPositionInLine(initialIndentToken.getCharPositionInLine());
			token.setStopIndex(next.getStartIndex()-1);
		}
		return token;
	}

	@Override
	public Token nextToken() {
		if (!tokenQueue.isEmpty()) { return tokenQueue.poll(); }

		Token next = super.nextToken();
		if (pendingDent && null == initialIndentToken && NewLine != next.getType()) { initialIndentToken = next; }
		if (null == next || HIDDEN == next.getChannel() || NewLine == next.getType()) { return next; }

		if (next.getType() == EOF) {
			indentCount = 0;
			if (!pendingDent) {
				initialIndentToken = next;
				tokenQueue.offer(createToken(NewLine, "NewLine", next));
			}
		}

		while (indentCount != getSavedIndent()) {
			if (indentCount > getSavedIndent()) {
				indentStack.push(indentCount);
				tokenQueue.offer(createToken(Indent, "Indent" + indentCount, next));
			} else {
				indentStack.pop();
				tokenQueue.offer(createToken(Dedent, "Dedent"+getSavedIndent(), next));
			}
		}
		pendingDent = false;
		tokenQueue.offer(next);
		return tokenQueue.poll();
	}

}

NewLine : ( '\r'? '\n' | '\r' | '\f' ) {
	if (pendingDent) { setChannel(HIDDEN); }
	pendingDent = true;
	indentCount = 0;
	initialIndentToken = null;
} ;

Spaces : [ \t]+ {
	setChannel(HIDDEN);
	if (pendingDent) { indentCount += getText().length(); }
} ;

Indent : 'Indent' { setChannel(HIDDEN); };
Dedent : 'Dedent' { setChannel(HIDDEN); };