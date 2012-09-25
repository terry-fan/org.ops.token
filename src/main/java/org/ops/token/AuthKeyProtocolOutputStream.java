package org.ops.token;

import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.lang.ClassNotFoundException;

public class AuthKeyProtocolOutputStream {
  private DataOutputStream ois;

  public AuthKeyProtocolOutputStream (DataOutputStream ois) {
      this.ois = ois;
  }

  public void writeData(AuthKey authKey) throws IOException {
      authKey.writeData(ois);
  }
}
